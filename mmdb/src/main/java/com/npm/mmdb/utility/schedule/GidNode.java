package com.npm.mmdb.utility.schedule;


import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidFile;

public class GidNode
{
	private final Gid				gid;
	private List<GidFile>	gidFiles;
	
	public GidNode(final Gid gid)
	{
		this.gid = gid;
		gidFiles = new LinkedList<>( );
	}
	
	public final Gid getGid( )
	{
		return gid;
	}
	
	public final void addGidFile(final GidFile gidFile)
	{
		gidFiles.add(gidFile);
	}
	
	public final void removeGidFile(final GidFile gidFile)
	{
		gidFiles.remove(gidFile);
	}
	
	public final GidFile getGidFile(final GidFile gidFile)
	{
		return gidFiles.get(gidFiles.indexOf(gidFile));
	}
	
	public final Set<GidFile> getGidFiles( )
	{
		return Collections.unmodifiableSet(new LinkedHashSet<>(gidFiles));
	}
	
	@Override
	public String toString( )
	{
		StringBuilder sb = new StringBuilder(gid.toString( ));
		for (GidFile gidFile : getGidFiles( ))
		{
			sb.append("\n").append(gidFile);
		}
		return sb.toString( );
	}
}
