package com.npm.mmdb.gui.core;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.ActivityIndicator;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Menu;
import org.apache.pivot.wtk.MenuHandler;
import org.apache.pivot.wtk.Prompt;

import com.npm.mmdb.gui.admin.DashboardScreen;
import com.npm.mmdb.utility.MmdbPrinter;


public class Bottomline extends BoxPane implements Bindable
{
	public interface BottomlineListener
	{

	}
	
	@BXML private ActivityIndicator	bottomlineActivityIndicator	= null;
	@BXML private Label				bottomlineLabel				= null;
	
	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
	}
	
	public final void startupBottomline( )
	{
		setMenuHandler(createMenuHandler( ));
	}
	
	private final MenuHandler createMenuHandler( )
	{
		return new MenuHandler.Adapter( )
		{
			@Override
			public boolean configureContextMenu(final Component component, final Menu menu, final int x, final int y)
			{
				Menu.Section menuSection = new Menu.Section( );
				menu.getSections( ).add(menuSection);
				Menu.Item menuItem = new Menu.Item("execute...");
				menuItem.setAction(new Action( )
				{
					@Override
					public void perform(final Component source)
					{
						Prompt.prompt("action executed", Bottomline.this.getWindow( ));
					}
				});
				menuSection.add(menuItem);
				return false;
			}
		};
	}
	
	public final void updateScreen(final DashboardScreen newScreen)
	{
		bottomlineLabel.setText("viewing " + newScreen.toString( ) + " screen");
	}
	
	public final void displayMessage(final String message)
	{
		bottomlineLabel.setText(MmdbPrinter.format(message));
	}

	public final void turnOnActivity( )
	{
		bottomlineActivityIndicator.setActive(true);
	}
	
	public final void turnOffActivity( )
	{
		bottomlineActivityIndicator.setActive(false);
	}
}
