package com.npm.mmdb.gui.home.gd2;


import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.gui.Mmdb;
import com.npm.mmdb.gui.home.listener.Gd2MlbScreenListener;


public class Gd2Reader extends TablePane implements Bindable
{
	private Mmdb					parent			= null;
	private Gd2MlbScreenListener	listener		= null;
	
	

	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		// TODO Auto-generated method stub
		
	}
	
	public final void startupGd2Reader(final Mmdb iParent)
	{
		parent = iParent;
	}
	
	public final void setGd2MlbScreenListener(final Gd2MlbScreenListener listener)
	{
		this.listener = listener;
	}
}