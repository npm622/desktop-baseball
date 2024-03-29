package com.npm.mmdb.utility;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class MmdbPrinter
{
	private static final SimpleDateFormat	SDF	= new SimpleDateFormat("|  yyyy-MM-dd  |  hh:mm:ss.SSS  |  ");
	
	public static final String format(final Object message)
	{
		return SDF.format(new Date( )) + message;
	}
	
	public static final String twoDigits(final Integer num)
	{
		if (num == null || num.intValue( ) < 0 || num.intValue( ) > 99)
		{
			throw new IllegalArgumentException("cannot form two digit numeral with " + num);
		}
		if (num.intValue( ) < 10)
		{
			return "0" + num;
		}
		else
		{
			return String.valueOf(num);
		}
	}
	
	public static final String fourDigits(final Integer num)
	{
		if (num == null || num.intValue( ) < -999 || num.intValue( ) > 9999)
		{
			throw new IllegalArgumentException("cannot form four digit numeral with " + num);
		}
		if (num.intValue( ) < -99 || num.intValue( ) > 999)
		{
			return String.valueOf(num);
		}
		else if (num.intValue( ) < -9 || num.intValue( ) > 99)
		{
			if (num.intValue( ) < 0)
			{
				return "-0" + Math.abs(num.intValue( ));
			}
			else
			{
				return "00" + num;
			}
		}
		else
		{
			if (num.intValue( ) < 0)
			{
				return "-00" + Math.abs(num.intValue( ));
			}
			else
			{
				return "000" + num;
			}
		}
	}

	public static final String formatXml(final Document xml)
	{
		TransformerFactory tf = TransformerFactory.newInstance( );
		Transformer transformer;
		try
		{
			transformer = tf.newTransformer( );
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			StreamResult result = new StreamResult(new ByteArrayOutputStream( ));
			
			transformer.transform(new DOMSource(xml), result);
			
			return new String(( (ByteArrayOutputStream) result.getOutputStream( ) ).toByteArray( ));
		}
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace( );
		}
		catch (TransformerException e)
		{
			e.printStackTrace( );
		}
		return xml.getNodeName( );
	}
}
