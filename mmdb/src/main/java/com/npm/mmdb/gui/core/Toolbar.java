package com.npm.mmdb.gui.core;


import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonGroup;
import org.apache.pivot.wtk.ButtonGroupListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.FillPane;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.gui.admin.DashboardScreen;


public class Toolbar extends TablePane implements Bindable
{
	@BXML private ImageView		logo			= null;
	@BXML private BoxPane		breadcrumbPane	= null;
	@BXML private ButtonGroup	buttonRowGroup	= null;
	@BXML private FillPane		contextPane		= null;
	@BXML private LinkButton	contextLink		= null;
	@BXML private BoxPane		actionPane		= null;
	

	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
	}
	
	public final void startupToolbar( )
	{
		initLogo( );
		initBreadcrumbPane( );
		initButtonRowGroup( );
		initContextPane( );
		initContextLink( );
		initActionPane( );
	}

	public final void updateScreen(final DashboardScreen newScreen)
	{
		updateButtonRow(newScreen.getContext( ));

		contextLink.setButtonData(newScreen.getContext( ));
		updateBreadcrumb(newScreen);
		updateActionLinks(newScreen);
	}
	
	private final void updateButtonRow(final DashboardScreen context)
	{
		if (context.equals(DashboardScreen.MMDB_HOME))
		{
			buttonRowGroup.setSelection(null);
		}
		else
		{
			for (Iterator<Button> buttonIter = buttonRowGroup.iterator( ) ; buttonIter.hasNext( ) ;)
			{
				Button button = buttonIter.next( );
				if (isButtonMatchedWithDashboardScreen(button, context))
				{
					buttonRowGroup.setSelection(button);
					break;
				}
			}
		}
	}

	private final void updateBreadcrumb(final DashboardScreen newScreen)
	{
		int counter = 0;
		boolean needsTrailingDelimiter = false;
		for (Iterator<Component> compIter = breadcrumbPane.iterator( ) ; compIter.hasNext( ) ;)
		{
			Component comp = compIter.next( );
			if (comp instanceof Button)
			{
				Button button = (Button) comp;
				if (isButtonContainedInDashboardScreenList(button, newScreen.getBreadcrumb( )))
				{
					button.setVisible(true);
					counter++;
					needsTrailingDelimiter = true;
				}
				else
				{
					button.setVisible(false);
					needsTrailingDelimiter = false;
				}
			}
			else
			{
				comp.setVisible(needsTrailingDelimiter && counter < newScreen.getBreadcrumb( ).size( ));
			}
		}
	}
	
	private final void updateActionLinks(final DashboardScreen newScreen)
	{
		for (Iterator<Component> compIter = actionPane.iterator( ) ; compIter.hasNext( ) ;)
		{
			Button button = (Button) compIter.next( );
			button.setVisible(isButtonContainedInDashboardScreenList(button, newScreen.getActions( )));
		}
	}

	private final void initLogo( )
	{
		logo.getComponentMouseButtonListeners( ).add(new ComponentMouseButtonListener.Adapter( )
		{
			@Override
			public boolean mouseClick(final Component component, final org.apache.pivot.wtk.Mouse.Button button,
					final int x, final int y, final int count)
			{
				Action.getNamedActions( ).get(DashboardScreen.MMDB_HOME.toString( )).perform(Toolbar.this);
				return super.mouseClick(component, button, x, y, count);
			}
		});
	}

	private final void initButtonRowGroup( )
	{
		buttonRowGroup.getButtonGroupListeners( ).add(new ButtonGroupListener.Adapter( )
		{
			@Override
			public void selectionChanged(final ButtonGroup buttonGroup, final Button previousSelection)
			{
				Action.getNamedActions( ).get(extractButtonRowData(buttonGroup.getSelection( ))).perform(Toolbar.this);
			}
		});
	}
	
	private final void initContextPane( )
	{
		for (DashboardScreen context : DashboardScreen.getContextSet( ))
		{
			if (context == DashboardScreen.MMDB_HOME)
			{
				continue;
			}
			PushButton button = new PushButton(true, context);
			button.setButtonGroup(buttonRowGroup);
			contextPane.add(button);
		}
	}
	
	private final void initContextLink( )
	{
		contextLink.getComponentMouseButtonListeners( ).add(new ComponentMouseButtonListener.Adapter( )
		{
			@Override
			public boolean mouseClick(final Component component, final org.apache.pivot.wtk.Mouse.Button button,
					final int x, final int y, final int count)
			{
				Action.getNamedActions( ).get(extractButtonRowData(buttonRowGroup.getSelection( )))
						.perform(Toolbar.this);
				return super.mouseClick(component, button, x, y, count);
			}
		});
	}
	
	private final void initBreadcrumbPane( )
	{
		breadcrumbPane.removeAll( );
		int i = 0;
		for (final DashboardScreen screen : DashboardScreen.values( ))
		{
			if (i++ != 0)
			{
				breadcrumbPane.add(new Label("  |  "));
			}
			LinkButton breadcrumbButton = new LinkButton(screen);
			breadcrumbButton.setAction(screen.toString( ));
			breadcrumbPane.add(breadcrumbButton);
		}
	}

	private final void initActionPane( )
	{
		actionPane.removeAll( );
		for (final DashboardScreen screen : DashboardScreen.values( ))
		{
			LinkButton actionButton = new LinkButton(screen);
			actionButton.setAction(screen.toString( ));
			actionPane.add(actionButton);
		}
	}
	
	private static final String extractButtonRowData(final Button selection)
	{
		if (selection != null)
		{
			return DashboardScreen.fromString(selection.getButtonData( ).toString( )).toString( );
		}
		else
		{
			return DashboardScreen.MMDB_HOME.toString( );
		}
	}
	
	private static final boolean isButtonMatchedWithDashboardScreen(final Button button, final DashboardScreen screen)
	{
		return button.getButtonData( ).toString( ).equals(screen.toString( ));
	}
	
	private static final boolean isButtonContainedInDashboardScreenList(final Button button,
			final List<DashboardScreen> screens)
	{
		return screens.contains(DashboardScreen.fromString(button.getButtonData( ).toString( )));
	}
}
