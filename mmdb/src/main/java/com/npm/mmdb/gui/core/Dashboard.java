package com.npm.mmdb.gui.core;


import java.net.URL;
import java.util.Iterator;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.CardPane;
import org.apache.pivot.wtk.Component;

import com.npm.mmdb.gui.admin.DashboardScreen;
import com.npm.mmdb.gui.home.Gd2MlbScreen;
import com.npm.mmdb.gui.home.MmdbHomeScreen;


public class Dashboard extends CardPane implements Bindable
{
	@BXML private MmdbHomeScreen	mmdbHomeScreen	= null;
	@BXML private Gd2MlbScreen		gd2MlbScreen	= null;

	@Override
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		// TODO Auto-generated method stub
		
	}
	
	public final void updateScreen(final DashboardScreen newScreen)
	{
		int index = 0;
		for (Iterator<Component> compIter = iterator( ) ; compIter.hasNext( ) ; index++)
		{
			Component comp = compIter.next( );
			if (comp == null)
			{
				System.out.println("say hey! null values");
			}
			else if (comp.getName( ) == null)
			{
				System.out.println("comp.getName( ) returns a null");
			}
			else
			{
				System.out.println(comp.getName( ));
			}
			if (comp.getName( ).equals(newScreen.toJavaString( ) + "Screen"))
			{
				setSelectedIndex(index);
			}
		}
	}
	
}
