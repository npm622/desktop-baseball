package com.npm.mmdb.gui.admin;


import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;


public interface InternalTask<T>
{
	public Task<T> getTask( );
	
	public TaskListener<T> getTaskListener( );
	
	public String getSuccessMessage( );
	
	public String getFailMessage( );
}
