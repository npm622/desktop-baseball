package com.npm.mmdb.utility.schedule;


import java.util.List;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.utility.schedule.CustomDateRange.CustomDateRangeBuilder;


public class MmdbScheduleTree
{
	public static void main(final String[ ] args)
	{
		MmdbScheduleTree tree = new MmdbScheduleTree(Source.WEB, Depth.MONTH, new CustomDateRangeBuilder(
				CustomDateRange.Method.SEQUENTIAL).start(2011, 6, 2).end(2014, 6, 22).build( ));
		tree.execute( );
		for (CalendarNode year : tree.getMonthsForYear(Integer.valueOf(2011)))
		{
			System.out.println(year.getValue( ));
		}
	}

	public static enum Source
	{
		WEB, DATABASE;
	}

	public static enum Depth
	{
		YEAR(0), MONTH(1), DAY(2), GID(3), GID_FILE(4);
		
		private final int	level;
		
		private Depth(final int level)
		{
			this.level = level;
		}
		
		public int getLevel( )
		{
			return level;
		}
		
		public static Depth byLevel(final int level)
		{
			for (Depth depth : Depth.values( ))
			{
				if (depth.getLevel( ) == level)
				{
					return depth;
				}
			}
			return null;
		}

		public Depth getNextLevel( )
		{
			return byLevel(getLevel( ) + 1);
		}
		
		@Override
		public String toString( )
		{
			return name( ).toString( );
		}
	}
	
	private final MmdbScheduleTree.Source	source;
	private final MmdbScheduleTree.Depth	depth;
	private final XMLGregorianCalendar		start;
	private final XMLGregorianCalendar		end;
	private CalendarNode					root;
	private ScheduleToolsValues				values;
	
	public MmdbScheduleTree(final MmdbScheduleTree.Source source, final MmdbScheduleTree.Depth depth,
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
		start = customDateRange.getStart( );
		end = customDateRange.getEnd( );
		values = new ScheduleToolsValues( );
	}
	
	public Set<CalendarNode> getYears( )
	{
		return root.getChildren( );
	}
	
	public Set<CalendarNode> getMonthsForYear(final Integer year)
	{
		return root.getChild(year).getChildren( );
	}
	
	public Set<CalendarNode> getDaysForYearMonth(final Integer year, final Integer month)
	{
		return root.getChild(year).getChild(month).getChildren( );
	}
	
	public Set<GidNode> getGidsForYearMonthDay(final Integer year, final Integer month, final Integer day)
	{
		return root.getChild(year).getChild(month).getChild(day).getGids( );
	}

	public final void execute( )
	{
		root = build(Depth.YEAR, new CalendarNode(null, null));
	}
	
	public final CalendarNode build(final Depth level, final CalendarNode node)
	{
		if (level == null || depth.getLevel( ) < level.getLevel( ))
		{
			return node;
		}
		if (level == Depth.YEAR || level == Depth.MONTH || level == Depth.DAY)
		{
			List<Integer> vals;
			switch (level)
			{
				case YEAR:
					vals = ScheduleTools.getAvailableYears(source, start, end);
					break;
				case MONTH:
					vals = ScheduleTools.getAvailableMonths(values.year, source, start, end);
					break;
				case DAY:
					vals = ScheduleTools.getAvailableDays(values.year, values.month, source, start, end);
					break;
				default:
					throw new IllegalStateException( );  // this scenario should be unreachable
			}
			for (Integer val : vals)
			{
				switch (level)
				{
					case YEAR:
						values.year = val;
						break;
					case MONTH:
						values.month = val;
						break;
					case DAY:
						values.day = val;
						break;
					default:
				}
				CalendarNode child = new CalendarNode(level, val);
				build(level.getNextLevel( ), child);
				node.addChild(child);
			}
		}
		else if (level == Depth.GID)
		{
			for (Gid gid : ScheduleTools.getAvailableGids(values.year, values.month, values.day, source))
			{
				GidNode gidNode = new GidNode(gid);
				for (GidFile gidFile : ScheduleTools.getAvailableGidFiles(gid, source))
				{
					gidNode.addGidFile(gidFile);
				}
				node.addGidNode(gidNode);
			}
		}
		return node;
	}
	
	private class ScheduleToolsValues
	{
		private Integer	year;
		private Integer	month;
		private Integer	day;
	}
}
