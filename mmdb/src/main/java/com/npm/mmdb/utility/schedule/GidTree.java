package com.npm.mmdb.utility.schedule;


import java.util.LinkedList;
import java.util.List;

import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Depth;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Source;


public class GidTree
{
	private Gid							gid;
	private final MmdbScheduleTree.Source	source;
	private final MmdbScheduleTree.Depth	depth;
	private List<GidFile>				gidFiles;
	
	public GidTree(final Gid gid, final MmdbScheduleTree.Source source, final MmdbScheduleTree.Depth depth)
	{
		this.gid = gid;
		this.source = source;
		this.depth = depth;
		gidFiles = new LinkedList<>( );
	}
	
	public final void execute( )
	{
		for (GidFile gidFile : ScheduleTools.getAvailableGidFiles(gid, source))
		{
			System.out.println(gidFile);
			if (depth == MmdbScheduleTree.Depth.GID_FILE)
			{
				addNewGidFile(gidFile);
			}
			else
			{
				throw new IllegalStateException(depth + " is not a supported enum value for building a ScheduleTree");
			}
		}
	}
	
	private final void addNewGidFile(final GidFile gidFile)
	{
		gidFiles.add(gidFile);
	}
	
	public final Gid getGid( )
	{
		return gid;
	}
	
	public final List<GidFile> getGidFiles( )
	{
		return gidFiles;
	}
	
	public final static GidFile getGidFileByVal(final String gidFileVal)
	{
		throw new UnsupportedOperationException(
				"need to implement the retrieval of gidFileByVal to retrieve " + gidFileVal);
	}
}
