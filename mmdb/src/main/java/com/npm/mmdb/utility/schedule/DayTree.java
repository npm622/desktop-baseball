package com.npm.mmdb.utility.schedule;


import java.util.LinkedList;
import java.util.List;

import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.utility.Gd2MlbTag;


public class DayTree
{
	private final Integer				year;
	private final Integer				month;
	private final Integer				day;
	private final MmdbScheduleTree.Source	source;
	private final MmdbScheduleTree.Depth	depth;
	private List<GidTree>					gidTrees;
	
	public DayTree(final Integer year, final Integer month, final Integer day, final MmdbScheduleTree.Source source,
			final MmdbScheduleTree.Depth depth)
	{
		this.year = year;
		this.month = month;
		this.day = day;
		this.source = source;
		this.depth = depth;
		gidTrees = new LinkedList<>( );
	}
	
	public final void execute( )
	{
		for (Gid gid : ScheduleTools.getAvailableGids(year, month, day, source))
		{
			System.out.println(gid);
			if (depth == MmdbScheduleTree.Depth.GID)
			{
				addNewGid(gid);
			}
			else
			{
				GidTree gidTree = new GidTree(gid, source, depth);
				gidTree.execute( );
				addGid(gidTree);
			}
		}
	}
	
	private final void addNewGid(final Gid gid)
	{
		gidTrees.add(new GidTree(gid, source, depth));
	}
	
	private final void addGid(final GidTree gidTree)
	{
		gidTrees.add(gidTree);
	}
	
	public final Integer getDay( )
	{
		return day;
	}
	
	public final List<GidTree> getGidTrees( )
	{
		return gidTrees;
	}
	
	public final GidTree getGidTreeByVal(final String gidVal)
	{
		for (GidTree gidTree : gidTrees)
		{
			if (Gd2MlbTag.buildGidUrlPart(gidTree.getGid( )).equals(gidVal))
			{
				return gidTree;
			}
		}
		return null;
	}
}
