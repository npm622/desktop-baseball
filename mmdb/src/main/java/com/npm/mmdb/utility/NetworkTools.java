package com.npm.mmdb.utility;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class NetworkTools
{
	public static final boolean hasInternetConnection( )
	{
		try
		{
			URL url = new URL("http://www.google.com/");
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
	
	public static final Elements getPageLinks(final String url) throws IOException
	{
		Connection connection = Jsoup.connect(url);
		Response response = connection.execute( );
		Document document = response.parse( );
		return document.getElementsByAttribute("href");
	}
}
