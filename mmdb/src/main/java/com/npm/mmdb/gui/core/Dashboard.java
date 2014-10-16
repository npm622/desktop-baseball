package com.npm.mmdb.gui.core;


import java.net.URL;
import java.util.Iterator;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.CardPane;
import org.apache.pivot.wtk.Component;

import com.npm.mmdb.gui.Mmdb;
import com.npm.mmdb.gui.admin.DashboardScreen;
import com.npm.mmdb.gui.core.listener.DashboardListener;
import com.npm.mmdb.gui.home.Db2Screen;
import com.npm.mmdb.gui.home.Gd2MlbScreen;
import com.npm.mmdb.gui.home.MmdbHomeScreen;
import com.npm.mmdb.gui.home.listener.Gd2MlbScreenListener;
import com.npm.mmdb.gui.performance.FantasyScreen;
import com.npm.mmdb.gui.performance.GameLogScreen;
import com.npm.mmdb.gui.performance.PerformanceScreen;
import com.npm.mmdb.gui.schedule.GameViewerScreen;
import com.npm.mmdb.gui.schedule.PlayByPlayScreen;
import com.npm.mmdb.gui.schedule.ScheduleScreen;
import com.npm.mmdb.gui.standings.StandingsScreen;
import com.npm.mmdb.gui.standings.TeamViewerScreen;


public class Dashboard extends CardPane implements Bindable
{
	private Mmdb					parent				= null;
	private DashboardListener		listener			= null;

	@BXML private MmdbHomeScreen	mmdbHomeScreen		= null;
	@BXML private Gd2MlbScreen		gd2MlbScreen		= null;
	@BXML private Db2Screen			db2Screen			= null;
	
	@BXML private StandingsScreen	standingsScreen		= null;
	@BXML private TeamViewerScreen	teamViewerScreen	= null;
	
	@BXML private ScheduleScreen	scheduleScreen		= null;
	@BXML private GameViewerScreen	gameViewerScreen	= null;
	@BXML private PlayByPlayScreen	playByPlayScreen	= null;
	
	@BXML private PerformanceScreen	performanceScreen	= null;
	@BXML private GameLogScreen		gameLogScreen		= null;
	@BXML private FantasyScreen		fantasyScreen		= null;

	@Override
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		
	}
	
	public final void startupDashboard(final Mmdb parent)
	{
		this.parent = parent;
		initListeners( );
		gd2MlbScreen.startupGd2MlbScreen(parent);
	}
	
	private final void initListeners( )
	{
		gd2MlbScreen.setGd2MlbScreenListener(new Gd2MlbScreenListener( )
		{
			@Override
			public <T> void backgroundTaskRequested(final Task<T> task, final TaskListener<T> taskListener)
			{
				if (listener != null)
				{
					listener.backgroundTaskRequested(task, taskListener);
				}
			}
			
			@Override
			public void sendToBottomline(final String message)
			{
				if (listener != null)
				{
					listener.sendToBottomline(message);
				}
			}
		});
	}

	public final void updateScreen(final DashboardScreen newScreen)
	{
		int index = 0;
		for (Iterator<Component> compIter = iterator( ) ; compIter.hasNext( ) ; index++)
		{
			Component comp = compIter.next( );
			if (comp.getName( ).equals(newScreen.toJavaString( ) + "Screen"))
			{
				setSelectedIndex(index);
			}
		}
	}
	
	public final void setDashboardListener(final DashboardListener listener)
	{
		this.listener = listener;
	}
}
