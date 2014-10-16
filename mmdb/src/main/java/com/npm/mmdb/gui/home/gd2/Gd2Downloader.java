package com.npm.mmdb.gui.home.gd2;


import java.net.URL;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.Accordion;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonGroup;
import org.apache.pivot.wtk.ButtonGroupListener;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.ComponentMouseListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.content.ButtonData;

import com.npm.mmdb.controller.GidController;
import com.npm.mmdb.gui.admin.InternalTask;
import com.npm.mmdb.gui.core.Dashboard.DashboardListener;
import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.utility.DateTools;
import com.npm.mmdb.utility.schedule.CalendarNode;
import com.npm.mmdb.utility.schedule.CustomDateRange;
import com.npm.mmdb.utility.schedule.CustomDateRange.CustomDateRangeBuilder;
import com.npm.mmdb.utility.schedule.GidNode;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree;
import com.npm.mmdb.utility.web.NetworkTools;


public class Gd2Downloader extends TablePane implements Bindable
{
	/*
	 * TODO - figure out a mechanism to know when all checkboxes are selected - select all checkbox doesn't unselect if
	 * all checkboxes aren't selected - disable submit button if no checkboxes are selected - open dialog when fetch
	 * button is clicked - send gids over to read panel
	 */
	public interface Gd2DownloaderListener extends DashboardListener
	{
		public void setActivePane( );
	}

	private enum DownloaderTreeTask
	{
		YEAR, MONTH, DAY, GID;
	}
	
	private Gd2DownloaderListener	listener			= null;
	private List<Gd2GidTableRow>	gidTableData		= null;
	
	@BXML private PushButton		refreshButton		= null;
	@BXML private Label				dbDate				= null;
	@BXML private PushButton		fetchButton			= null;
	@BXML private Label				internetStatus		= null;
	@BXML private ButtonGroup		yearButtonGroup		= null;
	@BXML private ButtonGroup		monthButtonGroup	= null;
	@BXML private ButtonGroup		dayButtonGroup		= null;
	@BXML private Accordion			accordionPane		= null;
	@BXML private TablePane			yearPane			= null;
	@BXML private TablePane			monthPane			= null;
	@BXML private TablePane			dayPane				= null;
	@BXML private ScrollPane		gidTablePane		= null;
	@BXML private TablePane			gidTable			= null;
	@BXML private Checkbox			gidSelectAll		= null;
	@BXML private PushButton		submitButton		= null;
	
	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		gidTableData = new LinkedList<>( );
	}
	
	public final void startupGd2Downloader( )
	{
		initRefresh( );
		initDbDate( );
		initFetch( );
		initButtonGroups( );
		initGidTable( );
		setYearButtonData( );
	}
	
	private final void initRefresh( )
	{
		refreshButton.getComponentMouseButtonListeners( ).add(new ComponentMouseButtonListener.Adapter( )
		{
			@Override
			public boolean mouseClick(final Component component, final org.apache.pivot.wtk.Mouse.Button button,
					final int x, final int y, final int count)
			{
				initFetch( );
				setYearButtonData( );
				initDbDate( );
				return false;
			}
		});
	}
	
	private final void initDbDate( )
	{
		dbDate.setText(new GidController( ).getDbDate( ).toString( ));
	}

	private final void initFetch( )
	{
		boolean hasInternet = NetworkTools.hasInternetConnection( );
		if (hasInternet)
		{
			internetStatus.setText("internet connected");
			internetStatus.getStyles( ).put("color", Integer.valueOf(17));
		}
		else
		{
			internetStatus.setText("internet not connected");
			internetStatus.getStyles( ).put("color", Integer.valueOf(20));
		}
		fetchButton.setEnabled(hasInternet);
	}

	private final void initButtonGroups( )
	{
		yearButtonGroup.getButtonGroupListeners( ).add(new ButtonGroupListener.Adapter( )
		{
			@Override
			public void selectionChanged(final ButtonGroup buttonGroup, final Button previousSelection)
			{
				if (monthButtonGroup.getSelection( ) != null)
				{
					monthButtonGroup.setSelection(null);
				}
				Accordion.setHeaderData(yearPane, new ButtonData("year    :  " + yearButtonGroup.getSelection( )
						.getButtonData( ).toString( )));
				accordionPane.setSelectedIndex(1);
				setMonthButtonData( );
			}
		});
		
		monthButtonGroup.getButtonGroupListeners( ).add(new ButtonGroupListener.Adapter( )
		{
			@Override
			public void selectionChanged(final ButtonGroup buttonGroup, final Button previousSelection)
			{
				if (dayButtonGroup.getSelection( ) != null)
				{
					dayButtonGroup.setSelection(null);
				}
				if (buttonGroup.getSelection( ) == null)
				{
					Accordion.setHeaderData(monthPane, new ButtonData("month :  --"));
				}
				else
				{
					Accordion.setHeaderData(monthPane, new ButtonData(
							"month :  " + getMonthStringButtonData(monthButtonGroup.getSelection( ))));
					accordionPane.setSelectedIndex(2);
					setDayButtonData( );
				}
			}
		});
		
		dayButtonGroup.getButtonGroupListeners( ).add(new ButtonGroupListener.Adapter( )
		{
			@Override
			public void selectionChanged(final ButtonGroup buttonGroup, final Button previousSelection)
			{
				if (buttonGroup.getSelection( ) == null)
				{
					gidTablePane.setVisible(false);
					submitButton.setVisible(false);
					Accordion.setHeaderData(dayPane, new ButtonData("day     :  --"));
				}
				else
				{
					Accordion.setHeaderData(dayPane, new ButtonData("day     :  " + dayButtonGroup.getSelection( )
							.getButtonData( ).toString( )));
					repaint( );
					setGidTableData( );
					gidTablePane.setVisible(true);
					submitButton.setVisible(true);
				}
			}
		});
	}
	
	private final void initGidTable( )
	{
		gidTable.getComponentMouseListeners( ).add(new ComponentMouseListener.Adapter( )
		{
			@Override
			public boolean mouseMove(final Component component, final int x, final int y)
			{
				int rowNum = gidTable.getRowAt(y);
				int i = 0;
				for (Row row : gidTable.getRows( ))
				{
					if (rowNum == 0)
					{
						continue;
					}
					row.setHighlighted(i++ == rowNum);
				}
				return false;
			}
		});
		
		gidSelectAll.getButtonPressListeners( ).add(new ButtonPressListener( )
		{
			@Override
			public void buttonPressed(final Button button)
			{
				for (Gd2GidTableRow row : gidTableData)
				{
					row.checkbox.setSelected(button.isSelected( ));
				}
			}
		});
	}

	private final void setYearButtonData( )
	{
		if (listener != null)
		{
			AccordionPaneTask task = new AccordionPaneTask(DownloaderTreeTask.YEAR);
			listener.backgroundTaskRequested(task.getTask( ), task.getTaskListener( ));
		}
	}
	
	private final void setMonthButtonData( )
	{
		if (listener != null)
		{
			AccordionPaneTask task = new AccordionPaneTask(DownloaderTreeTask.MONTH);
			listener.backgroundTaskRequested(task.getTask( ), task.getTaskListener( ));
		}
	}
	
	private final void setDayButtonData( )
	{
		if (listener != null)
		{
			AccordionPaneTask task = new AccordionPaneTask(DownloaderTreeTask.DAY);
			listener.backgroundTaskRequested(task.getTask( ), task.getTaskListener( ));
		}
	}
	
	private final void setGidTableData( )
	{
		if (listener != null)
		{
			AccordionPaneTask task = new AccordionPaneTask(DownloaderTreeTask.GID);
			listener.backgroundTaskRequested(task.getTask( ), task.getTaskListener( ));
		}
	}
	
	private final void fillGd2YearPane(final MmdbScheduleTree tree, final String message)
	{
		int rowCount = 0;
		for (CalendarNode year : tree.getYears( ))
		{
			PushButton button = new PushButton(true, year.getValue( ));
			button.setButtonGroup(yearButtonGroup);
			yearPane.getRows( ).get(rowCount++ % yearPane.getRows( ).getLength( )).add(button);
		}
		taskCompleted(message);
	}
	
	private final void fillGd2MonthPane(final MmdbScheduleTree tree, final String message)
	{
		Set<Integer> months = new HashSet<>( );
		for (CalendarNode month : tree.getMonthsForYear(Integer.valueOf(getYearSelected( ))))
		{
			months.add(month.getValue( ));
		}
		for (Iterator<Button> iter = monthButtonGroup.iterator( ) ; iter.hasNext( ) ;)
		{
			Button button = iter.next( );
			Integer monthNum = DateTools.convertMonthStringToInteger(getMonthStringButtonData(button));
			button.setEnabled(months.contains(monthNum));
		}
		taskCompleted(message);
	}
	
	private final void fillGd2DayPane(final MmdbScheduleTree tree, final String message)
	{
		int rowNum = 0;
		for (Row row : dayPane.getRows( ))
		{
			if (rowNum++ != 0)
			{
				row.remove(0, row.getLength( ));
			}
		}
		Set<Integer> days = new HashSet<>( );
		for (CalendarNode day : tree.getDaysForYearMonth(Integer.valueOf(getYearSelected( )),
				Integer.valueOf(getMonthSelected( ))))
		{
			days.add(day.getValue( ));
		}
		Calendar cal = Calendar.getInstance( );
		cal.set(getYearSelected( ), getMonthSelected( ) - 1, 1);
		int startOffset = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int totalIters = startOffset + cal.getActualMaximum(Calendar.DATE);
		for (int i = 0 ; i < totalIters ; i++)
		{
			Row row = dayPane.getRows( ).get(Double.valueOf(Math.floor(i / 7) + 1).intValue( ));
			if (i < startOffset)
			{
				row.add(new TablePane.Filler( ));
			}
			else
			{
				PushButton button = new PushButton(true, Integer.valueOf(i - startOffset + 1));
				button.setButtonGroup(dayButtonGroup);
				button.setEnabled(days.contains(Integer.valueOf(i - startOffset + 1)));
				row.add(button);
			}
		}
		taskCompleted(message);
	}
	
	private final void fillGd2GidPane(final MmdbScheduleTree tree, final String message)
	{
		gidTableData.clear( );
		gidTable.getRows( ).remove(1, gidTable.getRows( ).getLength( ) - 1);
		for (GidNode gidNode : tree.getGidsForYearMonthDay(Integer.valueOf(getYearSelected( )),
				Integer.valueOf(getMonthSelected( )), Integer.valueOf(getDaySelected( ))))
		{
			Gd2GidTableRow row = new Gd2GidTableRow(gidNode.getGid( ), gidNode.getGidFiles( ));
			gidTable.getRows( ).add(row);
			gidTableData.add(row);
		}
		taskCompleted(message);
	}

	private final int getYearSelected( )
	{
		return Integer.valueOf(yearButtonGroup.getSelection( ).getButtonData( ).toString( )).intValue( );
	}
	
	private final int getMonthSelected( )
	{
		String buttonData = monthButtonGroup.getSelection( ).getButtonData( ).toString( );
		String monthName = buttonData.replace("Button", "");
		return DateTools.convertMonthStringToInteger(monthName).intValue( );
	}
	
	private final int getDaySelected( )
	{
		return Integer.valueOf(dayButtonGroup.getSelection( ).getButtonData( ).toString( )).intValue( );
	}
	
	private final void taskCompleted(final String message)
	{
		if (listener != null)
		{
			listener.sendToBottomline(message);
		}
	}
	
	private final void taskFailed(final String message)
	{
		if (listener != null)
		{
			listener.sendToBottomline(message);
		}
	}
	
	public final void setListener(final Gd2DownloaderListener listener)
	{
		this.listener = listener;
	}

	private static final String getMonthStringButtonData(final Button monthButton)
	{
		return monthButton.getButtonData( ).toString( ).replace("Button", "");
	}
	
	private class Gd2GidTableRow extends TablePane.Row
	{
		private final Gid			gid;
		private final Set<GidFile>	gidFiles;
		private Checkbox			checkbox		= null;
		private PushButton			pushButton		= null;
		private Label				awayAbbrev		= null;
		private Label				awaySportCode	= null;
		private Label				homeAbbrev		= null;
		private Label				homeSportCode	= null;
		private Label				gameNumber		= null;
		private Label				note			= null;
		
		private Gd2GidTableRow(final Gid gid, final Set<GidFile> gidFiles)
		{
			this.gid = gid;
			this.gidFiles = gidFiles;
			checkbox = new Checkbox( );
			pushButton = new PushButton("#");
			awayAbbrev = new Label(gid.getAwayAbbrev( ));
			awaySportCode = new Label(gid.getAwaySportCode( ));
			homeAbbrev = new Label(gid.getHomeAbbrev( ));
			homeSportCode = new Label(gid.getHomeSportCode( ));
			gameNumber = new Label(gid.getGameNumber( ));
			if (gid.getNote( ) != null)
			{
				note = new Label(gid.getNote( ));
			}
			labelStyles(awayAbbrev, awaySportCode, homeAbbrev, homeSportCode, gameNumber);
			setup( );
		}
		
		private final void labelStyles(final Label... labels)
		{
			for (Label label : labels)
			{
				label.getStyles( ).put("horizontalAlignment", "center");
				label.getStyles( ).put("verticalAlignment", "center");
			}
		}

		private final void setup( )
		{
			BoxPane buttonPane = new BoxPane( );
			buttonPane.getStyles( ).put("horizontalAlignment", "center");
			buttonPane.getStyles( ).put("verticalAlignment", "center");
			buttonPane.getStyles( ).put("padding", Integer.valueOf(2));
			buttonPane.add(pushButton);
			pushButton.setPreferredSize(16, 16);
			setHeight(-1);
			add(buttonPane);
			BoxPane boxPane = new BoxPane( );
			boxPane.getStyles( ).put("horizontalAlignment", "center");
			boxPane.getStyles( ).put("verticalAlignment", "center");
			boxPane.add(checkbox);
			add(boxPane);
			add(awayAbbrev);
			add(awaySportCode);
			add(homeAbbrev);
			add(homeSportCode);
			add(gameNumber);
			if (note != null)
			{
				add(note);
			}
			else
			{
				add(new TablePane.Filler( ));
			}
		}
	}

	private class AccordionPaneTask implements InternalTask<MmdbScheduleTree>
	{
		private final DownloaderTreeTask	pane;
		
		private AccordionPaneTask(final DownloaderTreeTask pane)
		{
			this.pane = pane;
		}
		
		private MmdbScheduleTree getTree( )
		{
			switch (pane)
			{
				case YEAR:
					return new MmdbScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.YEAR,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).build( ));
				case MONTH:
					return new MmdbScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.MONTH,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).start(getYearSelected( ))
									.build( ));
				case DAY:
					return new MmdbScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.DAY,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).start(getYearSelected( ),
									getMonthSelected( )).build( ));
				case GID:
					return new MmdbScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.GID_FILE,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).start(getYearSelected( ),
									getMonthSelected( ), getDaySelected( )).build( ));
				default:
					return new MmdbScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.YEAR,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).build( ));
			}
		}
		
		@Override
		public String getSuccessMessage( )
		{
			switch (pane)
			{
				case YEAR:
					return "successfully retrieved years from the database";
				case MONTH:
					return "successfully set available months for " + yearButtonGroup.getSelection( ).getButtonData( );
				case DAY:
					return "successfully created calendar and set availabe days for " + monthButtonGroup.getSelection( )
							.getButtonData( ) + " " + yearButtonGroup.getSelection( ).getButtonData( );
				case GID:
					return "successfully populated gid table with the games selected for " + monthButtonGroup
							.getSelection( ).getButtonData( ) + " " + dayButtonGroup.getSelection( ).getButtonData( ) + ", " + yearButtonGroup
							.getSelection( ).getButtonData( );
				default:
					return "no success message available";
			}
		}
		
		@Override
		public String getFailMessage( )
		{
			return "task execution failed for " + pane + "'s AccordionPaneTask";
		}
		
		@Override
		public Task<MmdbScheduleTree> getTask( )
		{
			return new Task<MmdbScheduleTree>( )
			{
				@Override
				public MmdbScheduleTree execute( ) throws TaskExecutionException
				{
					MmdbScheduleTree tree = getTree( );
					tree.execute( );
					return tree;
				}
			};
		}
		
		@Override
		public TaskListener<MmdbScheduleTree> getTaskListener( )
		{
			return new TaskListener<MmdbScheduleTree>( )
			{
				@Override
				public void executeFailed(final Task<MmdbScheduleTree> task)
				{
					taskFailed(getFailMessage( ) + "  |  " + task.getFault( ).getLocalizedMessage( ));
				}
				
				@Override
				public void taskExecuted(final Task<MmdbScheduleTree> task)
				{
					switch (pane)
					{
						case YEAR:
							fillGd2YearPane(task.getResult( ), getSuccessMessage( ));
							break;
						case MONTH:
							fillGd2MonthPane(task.getResult( ), getSuccessMessage( ));
							break;
						case DAY:
							fillGd2DayPane(task.getResult( ), getSuccessMessage( ));
							break;
						case GID:
							fillGd2GidPane(task.getResult( ), getSuccessMessage( ));
							break;
					}
				}
			};
		}
	}
}
