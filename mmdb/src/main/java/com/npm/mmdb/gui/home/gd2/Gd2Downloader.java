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
import org.apache.pivot.wtk.ComponentMouseListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.content.ButtonData;

import com.npm.mmdb.gui.Mmdb;
import com.npm.mmdb.gui.admin.InternalTask;
import com.npm.mmdb.gui.home.listener.Gd2MlbScreenListener;
import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.utility.DateTools;
import com.npm.mmdb.utility.NetworkTools;
import com.npm.mmdb.utility.schedule.CustomDateRange;
import com.npm.mmdb.utility.schedule.CustomDateRange.CustomDateRangeBuilder;
import com.npm.mmdb.utility.schedule.DayTree;
import com.npm.mmdb.utility.schedule.GidTree;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree;
import com.npm.mmdb.utility.schedule.MonthTree;
import com.npm.mmdb.utility.schedule.ScheduleTree;
import com.npm.mmdb.utility.schedule.YearTree;


public class Gd2Downloader extends TablePane implements Bindable
{
	private enum DownloaderTreeTask
	{
		YEAR, MONTH, DAY, GID;
	}
	
	private Mmdb					parent					= null;
	private Gd2MlbScreenListener	listener				= null;
	private List<Gd2GidTableRow>	gd2GidTableData			= null;
	
	@BXML private Label				dbDateLabel				= null;
	@BXML private Label				internetStatusLabel		= null;
	@BXML private ButtonGroup		yearButtonGroup			= null;
	@BXML private ButtonGroup		monthButtonGroup		= null;
	@BXML private ButtonGroup		dayButtonGroup			= null;
	@BXML private Accordion			gd2AccordionPane		= null;
	@BXML private TablePane			gd2YearPane				= null;
	@BXML private TablePane			gd2MonthPane			= null;
	@BXML private TablePane			gd2DayPane				= null;
	@BXML private ScrollPane		gd2GidTableScrollPane	= null;
	@BXML private TablePane			gd2GidTable				= null;
	@BXML private Checkbox			gd2GidTableSelectAll	= null;
	@BXML private PushButton		submitToReadPaneButton	= null;
	
	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		gd2GidTableData = new LinkedList<>( );
	}
	
	public final void startupGd2Downloader(final Mmdb iParent)
	{
		parent = iParent;
		if (NetworkTools.hasInternetConnection( ))
		{
			internetStatusLabel.setText("internet connected");
			internetStatusLabel.getStyles( ).put("color", Integer.valueOf(17));
		}
		else
		{
			internetStatusLabel.setText("internet not connected");
			internetStatusLabel.getStyles( ).put("color", Integer.valueOf(20));
		}
		initButtonGroups( );
		setYearButtonData( );
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
				Accordion.setHeaderData(gd2YearPane, new ButtonData("year    :  " + yearButtonGroup.getSelection( )
						.getButtonData( ).toString( )));
				gd2AccordionPane.setSelectedIndex(1);
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
					Accordion.setHeaderData(gd2MonthPane, new ButtonData("month :  --"));
				}
				else
				{
					Accordion.setHeaderData(gd2MonthPane, new ButtonData(
							"month :  " + getMonthStringButtonData(monthButtonGroup.getSelection( ))));
					gd2AccordionPane.setSelectedIndex(2);
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
					gd2GidTableScrollPane.setVisible(false);
					submitToReadPaneButton.setVisible(false);
					Accordion.setHeaderData(gd2DayPane, new ButtonData("day     :  --"));
				}
				else
				{
					Accordion.setHeaderData(gd2DayPane, new ButtonData("day     :  " + dayButtonGroup.getSelection( )
							.getButtonData( ).toString( )));
					repaint( );
					setGidTableData( );
					gd2GidTableScrollPane.setVisible(true);
					submitToReadPaneButton.setVisible(true);
				}
			}
		});
		
		gd2GidTable.getComponentMouseListeners( ).add(new ComponentMouseListener.Adapter( )
		{
			@Override
			public boolean mouseMove(final Component component, final int x, final int y)
			{
				int rowNum = gd2GidTable.getRowAt(y);
				int i = 0;
				for (Row row : gd2GidTable.getRows( ))
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
		
		gd2GidTableSelectAll.getButtonPressListeners( ).add(new ButtonPressListener( )
		{
			@Override
			public void buttonPressed(final Button button)
			{
				for (Gd2GidTableRow row : gd2GidTableData)
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
	
	private final void fillGd2YearPane(final ScheduleTree tree, final String message)
	{
		int rowCount = 0;
		for (YearTree yearTree : tree.getYearTrees( ))
		{
			PushButton button = new PushButton(true, yearTree.getYear( ));
			button.setButtonGroup(yearButtonGroup);
			gd2YearPane.getRows( ).get(rowCount++ % gd2YearPane.getRows( ).getLength( )).add(button);
		}
		taskCompleted(message);
	}
	
	private final void fillGd2MonthPane(final ScheduleTree tree, final String message)
	{
		YearTree year = tree.getYearTreeByVal(Integer.valueOf(getYearSelected( )));
		Set<Integer> months = new HashSet<>( );
		for (MonthTree monthTree : year.getMonthTrees( ))
		{
			months.add(monthTree.getMonth( ));
		}
		for (Iterator<Button> iter = monthButtonGroup.iterator( ) ; iter.hasNext( ) ;)
		{
			Button button = iter.next( );
			Integer monthNum = DateTools.convertMonthStringToInteger(getMonthStringButtonData(button));
			button.setEnabled(months.contains(monthNum));
		}
		taskCompleted(message);
	}
	
	private final void fillGd2DayPane(final ScheduleTree tree, final String message)
	{
		int rowNum = 0;
		for (Row row : gd2DayPane.getRows( ))
		{
			if (rowNum++ != 0)
			{
				row.remove(0, row.getLength( ));
			}
		}
		MonthTree month = tree.getYearTreeByVal(Integer.valueOf(getYearSelected( ))).getMonthTreeByVal(
				Integer.valueOf(getMonthSelected( )));
		Set<Integer> days = new HashSet<>( );
		for (DayTree dayTree : month.getDayTrees( ))
		{
			days.add(dayTree.getDay( ));
		}
		Calendar cal = Calendar.getInstance( );
		cal.set(getYearSelected( ), getMonthSelected( ) - 1, 1);
		int startOffset = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int totalIters = startOffset + cal.getActualMaximum(Calendar.DATE);
		for (int i = 0 ; i < totalIters ; i++)
		{
			Row row = gd2DayPane.getRows( ).get(Double.valueOf(Math.floor(i / 7) + 1).intValue( ));
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
	
	private final void fillGd2GidPane(final ScheduleTree tree, final String message)
	{
		DayTree day = tree.getYearTreeByVal(Integer.valueOf(getYearSelected( )))
				.getMonthTreeByVal(Integer.valueOf(getMonthSelected( )))
				.getDayTreeByVal(Integer.valueOf(getDaySelected( )));
		gd2GidTable.getRows( ).remove(1, gd2GidTable.getRows( ).getLength( ) - 1);
		gd2GidTableData.clear( );
		for (GidTree gidTree : day.getGidTrees( ))
		{
			Gd2GidTableRow row = new Gd2GidTableRow(gidTree.getGid( ), gidTree.getGidFiles( ));
			gd2GidTable.getRows( ).add(row);
			gd2GidTableData.add(row);
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
	
	public final void setGd2MlbScreenListener(final Gd2MlbScreenListener listener)
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
		private final List<GidFile>	gidFiles;
		private Checkbox			checkbox		= null;
		private PushButton			pushButton		= null;
		private Label				awayAbbrev		= null;
		private Label				awaySportCode	= null;
		private Label				homeAbbrev		= null;
		private Label				homeSportCode	= null;
		private Label				gameNumber		= null;
		private Label				note			= null;
		
		Gd2GidTableRow(final Gid gid, final List<GidFile> gidFiles)
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
			buttonPane.getStyles( ).put("padding", 2);
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

	private class AccordionPaneTask implements InternalTask<ScheduleTree>
	{
		private final DownloaderTreeTask	pane;
		
		private AccordionPaneTask(final DownloaderTreeTask pane)
		{
			this.pane = pane;
		}
		
		private ScheduleTree getTree( )
		{
			switch (pane)
			{
				case YEAR:
					return new ScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.YEAR,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).build( ));
				case MONTH:
					return new ScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.MONTH,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).start(getYearSelected( ))
									.build( ));
				case DAY:
					return new ScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.DAY,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).start(getYearSelected( ),
									getMonthSelected( )).build( ));
				case GID:
					return new ScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.GID_FILE,
							new CustomDateRangeBuilder(CustomDateRange.Method.RECURSIVE).start(getYearSelected( ),
									getMonthSelected( ), getDaySelected( )).build( ));
				default:
					return new ScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.YEAR,
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
		public Task<ScheduleTree> getTask( )
		{
			return new Task<ScheduleTree>( )
			{
				@Override
				public ScheduleTree execute( ) throws TaskExecutionException
				{
					ScheduleTree tree = getTree( );
					tree.execute( );
					return tree;
				}
			};
		}
		
		@Override
		public TaskListener<ScheduleTree> getTaskListener( )
		{
			return new TaskListener<ScheduleTree>( )
			{
				@Override
				public void executeFailed(final Task<ScheduleTree> task)
				{
					taskFailed(getFailMessage( ) + "  |  " + task.getFault( ).getLocalizedMessage( ));
				}
				
				@Override
				public void taskExecuted(final Task<ScheduleTree> task)
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
