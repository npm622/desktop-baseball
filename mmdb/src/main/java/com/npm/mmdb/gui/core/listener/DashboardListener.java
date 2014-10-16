package com.npm.mmdb.gui.core.listener;


import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;


public interface DashboardListener
{
	public <T> void backgroundTaskRequested(Task<T> task, TaskListener<T> taskListener);

	public void sendToBottomline(String message);
}
