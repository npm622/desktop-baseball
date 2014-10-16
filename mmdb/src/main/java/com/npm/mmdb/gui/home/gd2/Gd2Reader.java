package com.npm.mmdb.gui.home.gd2;


import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.gui.core.Dashboard.DashboardListener;


public class Gd2Reader extends TablePane implements Bindable
{
	public interface Gd2ReaderListener extends DashboardListener
	{
		public void setActivePane( );
	}
	
	private Gd2ReaderListener	listener;

	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		// TODO Auto-generated method stub
		
	}
	
	public final void startupGd2Reader( )
	{
	}
	
	public final void setListener(final Gd2ReaderListener listener)
	{
		this.listener = listener;
	}
}