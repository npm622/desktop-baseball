package com.npm.mmdb.gui;


import java.awt.Dimension;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;


public class App implements Application
{
	private static final String		MAIN_FRAME_BXML	= "/com/npm/mmdb/bxml/app.bxml";
	private static final Dimension	DIM_1728_972	= new Dimension(1728, 972);
	
	private Window					window			= null;
	
	public static void main(final String[ ] args)
	{
		System.setProperty("org.apache.pivot.wtk.skin.terra.location", "/com/npm/mmdb/bxml/themes/TerraTheme_dark.json");
		DesktopApplicationContext.main(App.class, args);
	}
	
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		setupDisplayWindow(display);

		BXMLSerializer bxmlSerializer = new BXMLSerializer( );
		window = (Window) bxmlSerializer.readObject(App.class, MAIN_FRAME_BXML);
		window.open(display);
	}
	
	public boolean shutdown(final boolean arg0) throws Exception
	{
		if (window != null)
		{
			window.close( );
		}
		return false;
	}
	
	public void suspend( ) throws Exception
	{
		
	}
	
	public void resume( ) throws Exception
	{
		
	}
	
	private final static void setupDisplayWindow(final Display display)
	{
		display.getHostWindow( ).setSize(DIM_1728_972);
		display.getHostWindow( ).setLocationRelativeTo(null);
	}
}
