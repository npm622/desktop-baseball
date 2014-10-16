package com.npm.mmdb.utility.schedule;


import java.util.LinkedList;
import java.util.List;

import com.npm.mmdb.utility.schedule.CustomDateRange.CustomDateRangeBuilder;


public class ScheduleTree
{
	public static void main(final String[ ] args)
	{
		ScheduleTree tree = new ScheduleTree(MmdbScheduleTree.Source.DATABASE, MmdbScheduleTree.Depth.GID, new CustomDateRangeBuilder(
				CustomDateRange.Method.RECURSIVE).start(2014).build( ));
		tree.execute( );
	}

	private final MmdbScheduleTree.Source	source;
	private final MmdbScheduleTree.Depth	depth;
	private final CustomDateRange		customDateRange;
	private List<YearTree>				yearTrees;
	
	public ScheduleTree(final MmdbScheduleTree.Source source, final MmdbScheduleTree.Depth depth,
			final CustomDateRange customDateRange)
	{
		this.source = source;
		if (customDateRange.getStart( ) == null && customDateRange.getEnd( ) == null)
		{
			this.depth = MmdbScheduleTree.Depth.YEAR;
		}
		else
		{
			this.depth = depth;
		}
		this.customDateRange = customDateRange;
		yearTrees = new LinkedList<>( );
	}
	
	public final void execute( )
	{
		for (Integer year : ScheduleTools
				.getAvailableYears(source, customDateRange.getStart( ), customDateRange.getEnd( )))
		{
			System.out.println("y\t" + year);
			if (depth == MmdbScheduleTree.Depth.YEAR)
			{
				addNewYear(year);
			}
			else
			{
				YearTree yearTree = new YearTree(year, source, depth, customDateRange);
				yearTree.execute( );
				addYear(yearTree);
			}
		}
	}

	private final void addNewYear(final Integer year)
	{
		yearTrees.add(new YearTree(year, source, depth, customDateRange));
	}

	private final void addYear(final YearTree yearTree)
	{
		yearTrees.add(yearTree);
	}
	
	public final List<YearTree> getYearTrees( )
	{
		return yearTrees;
	}
	
	public final YearTree getYearTreeByVal(final Integer year)
	{
		for (YearTree yearTree : yearTrees)
		{
			if (yearTree.getYear( ).intValue( ) == year.intValue( ))
			{
				return yearTree;
			}
		}
		return null;
	}
}
