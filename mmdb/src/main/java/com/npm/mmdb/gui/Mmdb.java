package com.npm.mmdb.gui;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.gui.core.Bottomline;
import com.npm.mmdb.gui.core.Dashboard;
import com.npm.mmdb.gui.core.Toolbar;


public class Mmdb extends TablePane implements Bindable
{
	@BXML private Toolbar		toolbar		= null;
	@BXML private Dashboard		dashboard	= null;
	@BXML private Bottomline	bottomline	= null;
	
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		// TODO Auto-generated method stub
		
	}
	
}
