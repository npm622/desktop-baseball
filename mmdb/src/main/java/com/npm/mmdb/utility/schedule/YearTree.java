package com.npm.mmdb.utility.schedule;


import java.util.LinkedList;
import java.util.List;

import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Depth;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Source;


public class YearTree
{
	private final Integer				year;
	private final MmdbScheduleTree.Source	source;
	private final MmdbScheduleTree.Depth	depth;
	private final CustomDateRange		customDateRange;
	private List<MonthTree>				monthTrees;
	
	public YearTree(final Integer year, final MmdbScheduleTree.Source source, final MmdbScheduleTree.Depth depth, final CustomDateRange customDateRange)
	{
		this.year = year;
		this.source = source;
		this.depth = depth;
		this.customDateRange = customDateRange;
		monthTrees = new LinkedList<>( );
	}
	
	public final void execute( )
	{
		for (Integer month : ScheduleTools.getAvailableMonths(year, source, customDateRange.getStart( ),
				customDateRange.getEnd( )))
		{
			System.out.println("m\t" + month);
			if (depth == MmdbScheduleTree.Depth.MONTH)
			{
				addNewMonth(month);
			}
			else
			{
				MonthTree monthTree = new MonthTree(year, month, source, depth, customDateRange);
				monthTree.execute( );
				addMonth(monthTree);
			}
		}
	}
	
	private final void addNewMonth(final Integer month)
	{
		monthTrees.add(new MonthTree(year, month, source, depth, customDateRange));
	}
	
	private final void addMonth(final MonthTree monthTree)
	{
		monthTrees.add(monthTree);
	}
	
	public final Integer getYear( )
	{
		return year;
	}
	
	public final List<MonthTree> getMonthTrees( )
	{
		return monthTrees;
	}
	
	public final MonthTree getMonthTreeByVal(final Integer month)
	{
		for (MonthTree monthTree : monthTrees)
		{
			if (monthTree.getMonth( ).intValue( ) == month.intValue( ))
			{
				return monthTree;
			}
		}
		return null;
	}
}
