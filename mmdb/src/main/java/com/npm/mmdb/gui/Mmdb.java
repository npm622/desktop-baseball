package com.npm.mmdb.gui;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.Modifier;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.Window;

import com.npm.mmdb.gui.admin.DashboardScreen;
import com.npm.mmdb.gui.core.Bottomline;
import com.npm.mmdb.gui.core.Dashboard;
import com.npm.mmdb.gui.core.Toolbar;


public class Mmdb extends Window implements Bindable
{
	@BXML private Toolbar						toolbar		= null;
	@BXML private Dashboard						dashboard	= null;
	@BXML private Bottomline					bottomline	= null;

	@Override
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		startupMmdb( );
		initBottomline( );
	}
	
	public final void startupMmdb( )
	{
		for (DashboardScreen screen : DashboardScreen.values( ))
		{
			screen.setBreadcrumb( );
			screen.setLoadAction(createScreenUpdateAction(screen));
			getActionMappings( ).add(new ActionMapping(screen.getKeyStroke( ), screen.getLoadAction( )));
			Action.getNamedActions( ).put(screen.toString( ), screen.getLoadAction( ));
		}
		toolbar.startupToolbar(this);
		
		screenUpdated(DashboardScreen.MMDB_HOME);
	}

	private final Action createScreenUpdateAction(final DashboardScreen newScreen)
	{
		return new Action( )
		{
			@Override
			public void perform(final Component source)
			{
				screenUpdated(newScreen);
			}
		};
	}
	
	private final void initBottomline( )
	{
		Keyboard.Modifier ctrl = Modifier.CTRL;
		Keyboard.Modifier shift = Modifier.SHIFT;
		Keyboard.KeyStroke aStroke = new Keyboard.KeyStroke(Keyboard.KeyCode.A, ctrl.getMask( ) + shift.getMask( ));
		Action action = new Action( )
		{
			@Override
			public void perform(final Component source)
			{
				String message = "This is an action.";
				
				Prompt.prompt(message, Mmdb.this);
			}
		};
		getActionMappings( ).add(new ActionMapping(aStroke, action));
		bottomline.setActions(action);
	}
	
	private final void screenUpdated(final DashboardScreen newScreen)
	{
		toolbar.updateScreen(newScreen);
		dashboard.updateScreen(newScreen);
		// bottomline.updateScreen(newScreen);
	}
}
