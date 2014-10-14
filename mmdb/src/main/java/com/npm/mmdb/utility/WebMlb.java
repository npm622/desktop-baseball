package com.npm.mmdb.utility;


import java.net.HttpURLConnection;
import java.net.URL;


public class WebMlb
{
	public static final boolean hasInternetConnection( )
	{
		try
		{
			URL url = new URL("http://www.google.com");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection( );
			conn.getContent( );
		}
		catch (Exception e)
		{
			System.out.println(MmdbPrinter.format("system is not connected to the internet"));
			return false;
		}
		return true;
	}
}
