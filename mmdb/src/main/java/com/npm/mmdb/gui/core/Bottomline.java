package com.npm.mmdb.gui.core;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.FlowPane;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;


public class Bottomline extends FlowPane implements Bindable
{
	@BXML private Label			bottomlineLabel	= null;
	@BXML private PushButton	testButton		= null;
	
	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void setActions(final Action... actions)
	{
		int i = 0;
		for (Action action : actions)
		{
			if (i++ == 0)
			{
				testButton.setAction(action);
			}
		}
	}
}
