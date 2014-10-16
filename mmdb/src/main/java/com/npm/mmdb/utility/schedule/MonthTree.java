package com.npm.mmdb.utility.schedule;


import java.util.LinkedList;
import java.util.List;

import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Depth;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Source;


public class MonthTree
{
	private final Integer				year;
	private final Integer				month;
	private final MmdbScheduleTree.Source	source;
	private final MmdbScheduleTree.Depth	depth;
	private final CustomDateRange		customDateRange;
	private List<DayTree>				dayTrees;
	
	public MonthTree(final Integer year, final Integer month, final MmdbScheduleTree.Source source, final MmdbScheduleTree.Depth depth,
			final CustomDateRange customDateRange)
	{
		this.year = year;
		this.month = month;
		this.source = source;
		this.depth = depth;
		this.customDateRange = customDateRange;
		dayTrees = new LinkedList<>( );
	}
	
	public final void execute( )
	{
		for (Integer day : ScheduleTools.getAvailableDays(year, month, source, customDateRange.getStart( ),
				customDateRange.getEnd( )))
		{
			System.out.println("d\t" + day);
			if (depth == MmdbScheduleTree.Depth.DAY)
			{
				addNewDay(day);
			}
			else
			{
				DayTree dayTree = new DayTree(year, month, day, source, depth);
				dayTree.execute( );
				addDay(dayTree);
			}
		}
	}
	
	private final void addNewDay(final Integer day)
	{
		dayTrees.add(new DayTree(year, month, day, source, depth));
	}
	
	private final void addDay(final DayTree dayTree)
	{
		dayTrees.add(dayTree);
	}
	
	public final Integer getMonth( )
	{
		return month;
	}
	
	public final List<DayTree> getDayTrees( )
	{
		return dayTrees;
	}
	
	public final DayTree getDayTreeByVal(final Integer day)
	{
		for (DayTree dayTree : dayTrees)
		{
			if (dayTree.getDay( ).intValue( ) == day.intValue( ))
			{
				return dayTree;
			}
		}
		return null;
	}
}
