package com.npm.mmdb.utility;


import java.util.ArrayList;
import java.util.List;


public class MmdbNode<T>
{
	private final MmdbNode<T>	parent;
	private final T	root;
	private List<MmdbNode<T>>	children;
	
	public MmdbNode(final MmdbNode<T> parent, final T root)
	{
		this.parent = parent;
		this.root = root;
		children = new ArrayList<>( );
	}
	
	public final MmdbNode<T> getParent( )
	{
		return parent;
	}

	public final T getRoot( )
	{
		return root;
	}
	
	public final List<MmdbNode<T>> getChildren( )
	{
		return children;
	}

	public final void addChild(final MmdbNode<T> child)
	{
		children.add(child);
	}
	
	public final void setChildren(final List<MmdbNode<T>> children)
	{
		this.children = children;
	}
}
