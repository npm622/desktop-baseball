package com.npm.mmdb.utility.schedule;


import javax.xml.datatype.XMLGregorianCalendar;

import com.npm.mmdb.utility.BoolChecker;
import com.npm.mmdb.utility.DateTools;


public class CustomDateRange
{
	public static enum Method
	{
		RECURSIVE, SEQUENTIAL;
	}

	private final CustomDateRange.Method	method;
	private final XMLGregorianCalendar		start;
	private final XMLGregorianCalendar		end;
	
	private CustomDateRange(final CustomDateRangeBuilder builder)
	{
		method = builder.method;
		start = builder.startCal;
		end = builder.endCal;
	}
	
	public final CustomDateRange.Method getMethod( )
	{
		return method;
	}
	
	public final XMLGregorianCalendar getStart( )
	{
		return start;
	}
	
	public final XMLGregorianCalendar getEnd( )
	{
		return end;
	}

	public static class CustomDateRangeBuilder
	{
		private final Method			method;
		private CustomDate				start	= new CustomDate( );
		private CustomDate				end		= new CustomDate( );
		private XMLGregorianCalendar	startCal;
		private XMLGregorianCalendar	endCal;
		
		public CustomDateRangeBuilder(final Method method)
		{
			this.method = method;
		}

		public CustomDateRange build( )
		{
			if (start.isNull( ))
			{
				startCal = null;
				endCal = null;
				return new CustomDateRange(this);
			}

			startCal = DateTools.createXmlCalendar(start.year, start.month, start.day);
			switch (method)
			{
				case RECURSIVE:
					endCal = DateTools.createXmlCalendar(startCal.toString( ));
					break;
				case SEQUENTIAL:
					if (end.isNull( ))
					{
						endCal = DateTools.createXmlCalendarForToday( );
					}
					else
					{
						endCal = DateTools.createXmlCalendar(end.year, end.month, end.day);
					}
					break;
				default:
					throw new IllegalArgumentException("cannot build CustomDateRange for specified $Method: " + method);
			}
			return new CustomDateRange(this);
		}

		public CustomDateRangeBuilder start(final int year)
		{
			start.year = year;
			start.month = 0;
			start.day = 0;
			return this;
		}
		
		public CustomDateRangeBuilder start(final int year, final int month)
		{
			start.year = year;
			start.month = month;
			start.day = 0;
			return this;
		}
		
		public CustomDateRangeBuilder start(final int year, final int month, final int day)
		{
			start.year = year;
			start.month = month;
			start.day = day;
			return this;
		}
		
		public CustomDateRangeBuilder end(final int year)
		{
			end.year = year;
			end.month = 0;
			end.day = 0;
			return this;
		}
		
		public CustomDateRangeBuilder end(final int year, final int month)
		{
			end.year = year;
			end.month = month;
			end.day = 0;
			return this;
		}
		
		public CustomDateRangeBuilder end(final int year, final int month, final int day)
		{
			end.year = year;
			end.month = month;
			end.day = day;
			return this;
		}
	}

	private static class CustomDate
	{
		private int	year;
		private int	month;
		private int	day;

		private boolean isNull( )
		{
			return BoolChecker.checkIntsAllZero(year, month, day);
		}
	}
}
