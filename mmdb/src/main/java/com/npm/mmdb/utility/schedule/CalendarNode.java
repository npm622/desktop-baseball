package com.npm.mmdb.utility.schedule;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CalendarNode
{
	private final MmdbScheduleTree.Depth	depth;
	private final Integer					value;
	private Map<Integer, CalendarNode>		children;
	private List<GidNode>					gids;
	
	public CalendarNode(final MmdbScheduleTree.Depth depth, final Integer value)
	{
		this.depth = depth;
		this.value = value;
		children = new LinkedHashMap<>( );
		gids = new LinkedList<>( );
	}
	
	public final MmdbScheduleTree.Depth getDepth( )
	{
		return depth;
	}
	
	public final Integer getValue( )
	{
		return value;
	}
	
	public final void addChild(final CalendarNode child)
	{
		children.put(child.getValue( ), child);
	}
	
	public final void removeChild(final Integer val)
	{
		children.remove(val);
	}
	
	public final CalendarNode getChild(final Integer val)
	{
		return children.get(val);
	}
	
	public Set<CalendarNode> getChildren( )
	{
		return Collections.unmodifiableSet(new LinkedHashSet<>(children.values( )));
	}
	
	public final void addGidNode(final GidNode gidNode)
	{
		gids.add(gidNode);
	}
	
	public final void removeGidNode(final GidNode gidNode)
	{
		gids.remove(gidNode);
	}
	
	public final GidNode getGidNode(final GidNode gidNode)
	{
		return gids.get(gids.indexOf(gidNode));
	}
	
	public final Set<GidNode> getGids( )
	{
		return Collections.unmodifiableSet(new LinkedHashSet<>(gids));
	}

	@Override
	public String toString( )
	{
		StringBuilder sb = new StringBuilder(depth + "\t" + value);
		for (CalendarNode child : getChildren( ))
		{
			sb.append("\n").append(child.toString( ));
			for (GidNode gidNode : child.getGids( ))
			{
				sb.append("\n").append(gidNode);
			}
		}
		return sb.toString( );
	}
}
