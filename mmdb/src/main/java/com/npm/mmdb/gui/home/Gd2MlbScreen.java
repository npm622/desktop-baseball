package com.npm.mmdb.gui.home;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.gui.Mmdb;
import com.npm.mmdb.gui.home.gd2.Gd2Downloader;
import com.npm.mmdb.gui.home.gd2.Gd2Reader;
import com.npm.mmdb.gui.home.gd2.Gd2Uploader;
import com.npm.mmdb.gui.home.listener.Gd2MlbScreenListener;


public class Gd2MlbScreen extends TablePane implements Bindable
{
	private Mmdb					parent				= null;
	private Gd2MlbScreenListener	listener			= null;
	
	@BXML private ImageView			gd2DownloadActive	= null;
	@BXML private ImageView			gd2DownloadInactive	= null;
	@BXML private ImageView			gd2ReadActive		= null;
	@BXML private ImageView			gd2ReadInactive		= null;
	@BXML private ImageView			gd2UploadActive		= null;
	@BXML private ImageView			gd2UploadInactive	= null;
	@BXML private Gd2Downloader		gd2Downloader		= null;
	@BXML private Gd2Reader			gd2Reader			= null;
	@BXML private Gd2Uploader		gd2Uploader			= null;
	
	@Override
	public void initialize(final Map<String, Object> arg0, final URL arg1, final Resources arg2)
	{
		
	}
	
	public final void startupGd2MlbScreen(final Mmdb iParent)
	{
		parent = iParent;
		gd2Downloader.startupGd2Downloader(iParent);
		// gd2Reader.startupGd2Reader(iParent);
		// gd2Uploader.startupGd2Uploader(iParent);
	}
	
	public final void setGd2MlbScreenListener(final Gd2MlbScreenListener listener)
	{
		this.listener = listener;
		gd2Downloader.setGd2MlbScreenListener(listener);
	}
}
