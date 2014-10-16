package com.npm.mmdb.gui.home;


import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.TablePane;

import com.npm.mmdb.gui.core.Dashboard.DashboardListener;
import com.npm.mmdb.gui.home.gd2.Gd2Downloader;
import com.npm.mmdb.gui.home.gd2.Gd2Downloader.Gd2DownloaderListener;
import com.npm.mmdb.gui.home.gd2.Gd2Reader;
import com.npm.mmdb.gui.home.gd2.Gd2Reader.Gd2ReaderListener;
import com.npm.mmdb.gui.home.gd2.Gd2Uploader;
import com.npm.mmdb.gui.home.gd2.Gd2Uploader.Gd2UploaderListener;


public class Gd2MlbScreen extends TablePane implements Bindable
{
	public interface Gd2MlbScreenListener extends DashboardListener
	{
		
	}
	
	public enum Gd2MlbPane
	{
		DOWNLOAD, READ, UPLOAD;
	}

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
	
	public final void startupGd2MlbScreen( )
	{
		gd2Downloader.startupGd2Downloader( );
		// gd2Reader.startupGd2Reader();
		// gd2Uploader.startupGd2Uploader();
	}
	
	private final void updatePaneBar(final Gd2MlbPane activePane)
	{
		switch (activePane)
		{
			case DOWNLOAD:
				setDownloadActive(true);
				setReadActive(false);
				setUploadActive(false);
				break;
			case READ:
				setDownloadActive(false);
				setReadActive(true);
				setUploadActive(false);
				break;
			case UPLOAD:
				setDownloadActive(false);
				setReadActive(false);
				setUploadActive(true);
				break;
		}
	}
	
	private final void setDownloadActive(final boolean isActive)
	{
		gd2DownloadActive.setVisible(isActive);
		gd2DownloadInactive.setVisible(!isActive);
	}
	
	private final void setReadActive(final boolean isActive)
	{
		gd2ReadActive.setVisible(isActive);
		gd2ReadInactive.setVisible(!isActive);
	}
	
	private final void setUploadActive(final boolean isActive)
	{
		gd2UploadActive.setVisible(isActive);
		gd2UploadInactive.setVisible(!isActive);
	}

	public final void setListener(final Gd2MlbScreenListener iListener)
	{
		listener = iListener;
		gd2Downloader.setListener(new Gd2DownloaderListener( )
		{
			@Override
			public <T> void backgroundTaskRequested(final Task<T> task, final TaskListener<T> taskListener)
			{
				listener.backgroundTaskRequested(task, taskListener);
			}
			
			@Override
			public void sendToBottomline(final String message)
			{
				listener.sendToBottomline(message);
			}
			
			@Override
			public void setActivePane( )
			{
				updatePaneBar(Gd2MlbPane.DOWNLOAD);
			}
		});
		gd2Reader.setListener(new Gd2ReaderListener( )
		{
			@Override
			public void sendToBottomline(final String message)
			{
				listener.sendToBottomline(message);
				
			}
			
			@Override
			public <T> void backgroundTaskRequested(final Task<T> task, final TaskListener<T> taskListener)
			{
				listener.backgroundTaskRequested(task, taskListener);
			}
			
			@Override
			public void setActivePane( )
			{
				updatePaneBar(Gd2MlbPane.READ);
			}
		});
		gd2Uploader.setListener(new Gd2UploaderListener( )
		{
			@Override
			public void sendToBottomline(final String message)
			{
				listener.sendToBottomline(message);
			}
			
			@Override
			public <T> void backgroundTaskRequested(final Task<T> task, final TaskListener<T> taskListener)
			{
				listener.backgroundTaskRequested(task, taskListener);
			}
			
			@Override
			public void setActivePane( )
			{
				updatePaneBar(Gd2MlbPane.UPLOAD);
			}
		});
	}
}
