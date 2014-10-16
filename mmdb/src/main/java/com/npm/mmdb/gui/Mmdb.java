package com.npm.mmdb.gui;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.TaskAdapter;
import org.apache.pivot.wtk.Window;

import com.npm.mmdb.gui.admin.DashboardScreen;
import com.npm.mmdb.gui.core.Bottomline;
import com.npm.mmdb.gui.core.Dashboard;
import com.npm.mmdb.gui.core.Toolbar;
import com.npm.mmdb.gui.core.listener.DashboardListener;


public class Mmdb extends Window implements Bindable
{
	@BXML private Toolbar		toolbar		= null;
	@BXML private Dashboard		dashboard	= null;
	@BXML private Bottomline	bottomline	= null;

	@Override
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		startupMmdb( );
	}
	
	public final void startupMmdb( )
	{
		initListeners( );
		for (DashboardScreen screen : DashboardScreen.values( ))
		{
			screen.setBreadcrumb( );
			screen.setLoadAction(createScreenUpdateAction(screen));
			getActionMappings( ).add(new ActionMapping(screen.getKeyStroke( ), screen.getLoadAction( )));
			Action.getNamedActions( ).put(screen.toString( ), screen.getLoadAction( ));
		}
		toolbar.startupToolbar(this);
		dashboard.startupDashboard(this);
		bottomline.startupBottomline(this);
		Action.getNamedActions( ).get(DashboardScreen.MMDB_HOME.toString( )).perform(this);
	}
	
	private final void initListeners( )
	{
		dashboard.setDashboardListener(new DashboardListener( )
		{
			@Override
			public <T> void backgroundTaskRequested(final Task<T> task, final TaskListener<T> taskListener)
			{
				bottomline.turnOnActivity( );
				task.execute(new TaskAdapter<>(taskListener));
			}
			
			@Override
			public void sendToBottomline(final String message)
			{
				bottomline.turnOffActivity( );
				bottomline.displayMessage(message);
			}
		});
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
	
	private final void screenUpdated(final DashboardScreen newScreen)
	{
		toolbar.updateScreen(newScreen);
		dashboard.updateScreen(newScreen);
		bottomline.updateScreen(newScreen);
	}
}
