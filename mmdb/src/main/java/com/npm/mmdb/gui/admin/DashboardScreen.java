package com.npm.mmdb.gui.admin;


import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyStroke;
import org.apache.pivot.wtk.Keyboard.Modifier;


public enum DashboardScreen
{
	MMDB_HOME,
	GD2_MLB,
	DB_2,
	STANDINGS,
	TEAM_VIEWER,
	SCHEDULE,
	GAME_VIEWER,
	PLAY_BY_PLAY,
	PERFORMANCE,
	GAME_LOG,
	FANTASY;
	
	private List<DashboardScreen>	breadcrumb	= new LinkedList<>( );
	private Action					loadAction;
	
	public final void setBreadcrumb( )
	{
		breadcrumb.clear( );
		switch (this)
		{
			case MMDB_HOME:
				breadcrumb.add(MMDB_HOME);
				break;
			case GD2_MLB:
				breadcrumb.add(MMDB_HOME);
				breadcrumb.add(GD2_MLB);
				break;
			case DB_2:
				breadcrumb.add(MMDB_HOME);
				breadcrumb.add(DB_2);
				break;
			case STANDINGS:
				breadcrumb.add(STANDINGS);
				break;
			case TEAM_VIEWER:
				breadcrumb.add(STANDINGS);
				breadcrumb.add(TEAM_VIEWER);
				break;
			case SCHEDULE:
				breadcrumb.add(SCHEDULE);
				break;
			case GAME_VIEWER:
				breadcrumb.add(SCHEDULE);
				breadcrumb.add(GAME_VIEWER);
				break;
			case PLAY_BY_PLAY:
				breadcrumb.add(SCHEDULE);
				breadcrumb.add(PLAY_BY_PLAY);
				break;
			case PERFORMANCE:
				breadcrumb.add(PERFORMANCE);
				break;
			case GAME_LOG:
				breadcrumb.add(PERFORMANCE);
				breadcrumb.add(GAME_LOG);
				break;
			case FANTASY:
				breadcrumb.add(PERFORMANCE);
				breadcrumb.add(FANTASY);
				break;
		}
	}

	public final Action getLoadAction( )
	{
		return loadAction;
	}
	
	public final void setLoadAction(final Action action)
	{
		loadAction = action;
	}

	public final List<DashboardScreen> getBreadcrumb( )
	{
		return breadcrumb;
	}
	
	public final DashboardScreen getContext()
	{
		switch (this)
		{
			case MMDB_HOME:
			case GD2_MLB:
			case DB_2:
				return MMDB_HOME;
			case STANDINGS:
			case TEAM_VIEWER:
				return STANDINGS;
			case SCHEDULE:
			case GAME_VIEWER:
			case PLAY_BY_PLAY:
				return SCHEDULE;
			case PERFORMANCE:
			case GAME_LOG:
			case FANTASY:
				return PERFORMANCE;
			default:
				throw new IllegalArgumentException(this + " is not a supported enum for DashboardScreen#getContext( )");
		}
	}
	
	public final List<DashboardScreen> getActions( )
	{
		LinkedList<DashboardScreen> actions = new LinkedList<>( );
		switch (getContext( ))
		{
			case MMDB_HOME:
				actions.add(GD2_MLB);
				actions.add(DB_2);
				return actions;
			case STANDINGS:
				actions.add(TEAM_VIEWER);
				return actions;
			case SCHEDULE:
				actions.add(GAME_VIEWER);
				actions.add(PLAY_BY_PLAY);
				return actions;
			case PERFORMANCE:
				actions.add(GAME_LOG);
				actions.add(FANTASY);
				return actions;
			default:
				throw new IllegalArgumentException(this + " is not a supported enum for DashboardScreen#getActions( )");
		}
	}

	private final int getKeyCode( )
	{
		switch (this)
		{
			case MMDB_HOME:
				return Keyboard.KeyCode.M;
			case GD2_MLB:
				return Keyboard.KeyCode.W;
			case DB_2:
				return Keyboard.KeyCode.D;
			case STANDINGS:
				return Keyboard.KeyCode.S;
			case TEAM_VIEWER:
				return Keyboard.KeyCode.T;
			case SCHEDULE:
				return Keyboard.KeyCode.C;
			case GAME_VIEWER:
				return Keyboard.KeyCode.G;
			case PLAY_BY_PLAY:
				return Keyboard.KeyCode.Y;
			case PERFORMANCE:
				return Keyboard.KeyCode.P;
			case GAME_LOG:
				return Keyboard.KeyCode.L;
			case FANTASY:
				return Keyboard.KeyCode.F;
			default:
				throw new IllegalArgumentException(this + " is not a supported enum for DashboardScreen#getKeyCode( )");
		}
	}
	
	public final KeyStroke getKeyStroke( )
	{
		int modifier = Modifier.SHIFT.getMask( );
		if (breadcrumb.size( ) == 1)
		{
			modifier += Modifier.CTRL.getMask( );
		}
		else
		{
			modifier += Modifier.ALT.getMask( );
		}
		return new Keyboard.KeyStroke(getKeyCode( ), modifier);
	}

	public static final DashboardScreen fromString(final String value)
	{
		return DashboardScreen.valueOf(value.toUpperCase( ).replace("-", "_"));
	}
	
	public static final Set<DashboardScreen> getContextSet( )
	{
		Set<DashboardScreen> contextSet = new LinkedHashSet<>( );
		for (DashboardScreen screen : DashboardScreen.values( ))
		{
			contextSet.add(screen.getContext( ));
		}
		return contextSet;
	}

	@Override
	public String toString( )
	{
		return name( ).toLowerCase( ).replace("_", "-");
	}
	
	public String toJavaString( )
	{
		StringBuilder sb = new StringBuilder( );
		int counter = 0;
		for (String part : name( ).split("_"))
		{
			if (counter++ == 0)
			{
				sb.append(part.toLowerCase( ));
			}
			else
			{
				if (part.length( ) == 1)
				{
					sb.append(part.toUpperCase( ));
				}
				else
				{
					sb.append(part.substring(0, 1).toUpperCase( ) + part.substring(1).toLowerCase( ));
				}
			}
		}
		return sb.toString( );
	}
}
