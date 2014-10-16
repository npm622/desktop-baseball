package com.npm.mmdb.utility;


import java.util.Calendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class DateTools
{
	private static final DatatypeFactory	DATATYPE_FACTORY;
	static
	{
		try
		{
			DATATYPE_FACTORY = DatatypeFactory.newInstance( );
		}
		catch (DatatypeConfigurationException e)
		{
			throw new IllegalStateException(e);
		}
	}
	
	public static final XMLGregorianCalendar createXmlCalendarForToday( )
	{
		Calendar cal = Calendar.getInstance( );
		return createXmlCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
	}

	public static final XMLGregorianCalendar createXmlCalendar(final int year, final int month, final int day)
	{
		StringBuilder sb = new StringBuilder( );
		if (year > 0)
		{
			sb.append(MmdbPrinter.fourDigits(Integer.valueOf(year)));
			if (month > 0)
			{
				sb.append("-").append(MmdbPrinter.twoDigits(Integer.valueOf(month)));
				if (day > 0)
				{
					sb.append("-").append(MmdbPrinter.twoDigits(Integer.valueOf(day)));
				}
			}
		}
		return createXmlCalendar(sb.toString( ));
	}
	
	public static final XMLGregorianCalendar createXmlCalendar(final String lexicalRepresentation)
	{
		return DATATYPE_FACTORY.newXMLGregorianCalendar(lexicalRepresentation);
	}
	
	public static final int getMaximumDayOfMonth(final Integer year, final Integer month)
	{
		Calendar cal = Calendar.getInstance( );
		cal.set(Calendar.YEAR, year.intValue( ));
		cal.set(Calendar.MONTH, month.intValue( ) - 1);
		return cal.getActualMaximum(Calendar.DATE);
	}
	
	public static final Integer convertMonthStringToInteger(final String month)
	{
		if (month.equalsIgnoreCase("january"))
		{
			return Integer.valueOf(1);
		}
		else if (month.equalsIgnoreCase("february"))
		{
			return Integer.valueOf(2);
		}
		else if (month.equalsIgnoreCase("march"))
		{
			return Integer.valueOf(3);
		}
		else if (month.equalsIgnoreCase("april"))
		{
			return Integer.valueOf(4);
		}
		else if (month.equalsIgnoreCase("may"))
		{
			return Integer.valueOf(5);
		}
		else if (month.equalsIgnoreCase("june"))
		{
			return Integer.valueOf(6);
		}
		else if (month.equalsIgnoreCase("july"))
		{
			return Integer.valueOf(7);
		}
		else if (month.equalsIgnoreCase("august"))
		{
			return Integer.valueOf(8);
		}
		else if (month.equalsIgnoreCase("september"))
		{
			return Integer.valueOf(9);
		}
		else if (month.equalsIgnoreCase("october"))
		{
			return Integer.valueOf(10);
		}
		else if (month.equalsIgnoreCase("november"))
		{
			return Integer.valueOf(11);
		}
		else if (month.equalsIgnoreCase("december"))
		{
			return Integer.valueOf(12);
		}
		else
		{
			throw new IllegalArgumentException("cannot convert " + month + " to an integer value of month");
		}
	}
}
