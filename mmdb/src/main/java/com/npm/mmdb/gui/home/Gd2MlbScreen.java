package com.npm.mmdb.gui.home;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ButtonGroup;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.utility.WebMlb;


public class Gd2MlbScreen extends TablePane implements Bindable
{
	@BXML private ImageView		gd2DownloadActive	= null;
	@BXML private ImageView		gd2DownloadInactive	= null;
	@BXML private ImageView		gd2ReadActive		= null;
	@BXML private ImageView		gd2ReadInactive		= null;
	@BXML private ImageView		gd2UploadActive		= null;
	@BXML private ImageView		gd2UploadInactive	= null;
	@BXML private Label			dbDateLabel			= null;
	@BXML private Label			internetStatusLabel	= null;
	@BXML private ButtonGroup	yearButtonGroup		= null;
	@BXML private ButtonGroup	monthButtonGroup	= null;
	@BXML private ButtonGroup	dayButtonGroup		= null;
	@BXML private TablePane		gd2YearPane			= null;
	@BXML private TablePane		gd2MonthPane		= null;
	@BXML private TablePane		gd2DayPane			= null;
	@BXML private TablePane		gd2GidTable			= null;
	
	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		// TODO Auto-generated method stub
		
	}
	
	public final void startupGd2MlbScreen( )
	{
		if (WebMlb.hasInternetConnection( ))
		{
			internetStatusLabel.setText("internet connected");
			internetStatusLabel.getStyles( ).put("color", Integer.valueOf(17));
		}
		else
		{
			internetStatusLabel.setText("internet not connected");
			internetStatusLabel.getStyles( ).put("color", Integer.valueOf(20));
		}
	}

}
