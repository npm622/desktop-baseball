package com.npm.mmdb.utility.schedule;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.npm.mmdb.controller.GidController;
import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.utility.DateTools;
import com.npm.mmdb.utility.Gd2MlbTag;
import com.npm.mmdb.utility.WebMlb;
import com.npm.mmdb.utility.schedule.MmdbScheduleTree.Source;


public class ScheduleTools
{

	public static final List<Integer> getAvailableYears(final MmdbScheduleTree.Source source,
			final XMLGregorianCalendar start, final XMLGregorianCalendar end)
	{
		List<Integer> years = new ArrayList<>( );
		switch (source)
		{
			case WEB:
				try
				{
					years = WebMlb.getDateNumbersFromPageLinks(Gd2MlbTag.ROOT.getTag( ), Gd2MlbTag.YEAR);
				}
				catch (IOException e)
				{
					throw new IllegalStateException(e);
				}
				break;
			case DATABASE:
				years = new GidController( ).getGidDbYears( );
				break;
			default:
				throw new IllegalArgumentException(
						source + " is not a supported enum value for DateTools$getAvailableYears(...)");
		}
		Collections.reverse(years);
		if (start == null)
		{
			return years;
		}
		List<Integer> availableYears = new LinkedList<>( );
		for (int i = start.getYear( ) ; i <= end.getYear( ) ; i++)
		{
			if (years.contains(Integer.valueOf(i)))
			{
				availableYears.add(Integer.valueOf(i));
			}
		}
		return availableYears;
	}

	public static List<Integer> getAvailableMonths(final Integer year, final MmdbScheduleTree.Source source,
			final XMLGregorianCalendar start, final XMLGregorianCalendar end)
	{
		List<Integer> months = new ArrayList<>( );
		switch (source)
		{
			case WEB:
				try
				{
					months = WebMlb.getDateNumbersFromPageLinks(Gd2MlbTag.buildUrl(year), Gd2MlbTag.MONTH);
				}
				catch (IOException e)
				{
					throw new IllegalStateException(e);
				}
				break;
			case DATABASE:
				months = new GidController( ).getGidDbMonths(year);
				break;
			default:
				throw new IllegalArgumentException(
						source + " is not a supported enum value for DateTools$getAvailableYears(...)");
		}
		List<Integer> availableMonths = new LinkedList<>( );
		int startMonth = 1;
		int endMonth = 12;
		if (year.intValue( ) == start.getYear( ) && start.getMonth( ) > 0)
		{
			startMonth = start.getMonth( );
		}
		if (year.intValue( ) == end.getYear( ) && end.getMonth( ) > 0)
		{
			endMonth = end.getMonth( );
		}
		for (int i = startMonth ; i <= endMonth ; i++)
		{
			if (months.contains(Integer.valueOf(i)))
			{
				availableMonths.add(Integer.valueOf(i));
			}
		}
		return availableMonths;
	}

	public static List<Integer> getAvailableDays(final Integer year, final Integer month, final MmdbScheduleTree.Source source,
			final XMLGregorianCalendar start, final XMLGregorianCalendar end)
	{
		List<Integer> days = new ArrayList<>( );
		switch (source)
		{
			case WEB:
				try
				{
					days = WebMlb.getDateNumbersFromPageLinks(Gd2MlbTag.buildUrl(year, month), Gd2MlbTag.DAY);
				}
				catch (IOException e)
				{
					throw new IllegalStateException(e);
				}
				break;
			case DATABASE:
				days = new GidController( ).getGidDbDays(year, month);
				break;
			default:
				throw new IllegalArgumentException(
						source + " is not a supported enum value for DateTools$getAvailableYears(...)");
		}
		List<Integer> availableDays = new LinkedList<>( );
		int startDay = 1;
		int endDay = DateTools.getMaximumDayOfMonth(year, month);
		if (year.intValue( ) == start.getYear( ) && month.intValue( ) == start.getMonth( ) && start.getDay( ) > 0)
		{
			startDay = start.getDay( );
		}
		if (year.intValue( ) == end.getYear( ) && month.intValue( ) == end.getMonth( ) && end.getDay( ) > 0)
		{
			endDay = end.getDay( );
		}
		for (int i = startDay ; i <= endDay ; i++)
		{
			if (days.contains(Integer.valueOf(i)))
			{
				availableDays.add(Integer.valueOf(i));
			}
		}
		return availableDays;
	}

	public static List<Gid> getAvailableGids(final Integer year, final Integer month, final Integer day,
			final MmdbScheduleTree.Source source)
	{
		List<Gid> gids = new ArrayList<>( );
		switch (source)
		{
			case WEB:
				try
				{
					gids = WebMlb.getGidsFromDayPage(year, month, day);
				}
				catch (IOException e)
				{
					throw new IllegalStateException(e);
				}
				break;
			case DATABASE:
				gids = new GidController( ).getGidDbGids(year, month, day);
				break;
			default:
				throw new IllegalArgumentException(
						source + " is not a supported enum value for DateTools$getAvailableYears(...)");
		}
		return gids;
	}

	public static List<GidFile> getAvailableGidFiles(final Gid gid, final MmdbScheduleTree.Source source)
	{
		List<GidFile> gidFiles = new ArrayList<>( );
		switch (source)
		{
			case WEB:
				try
				{
					gidFiles = WebMlb.getGidFilesFromGidPage(gid);
				}
				catch (IOException e)
				{
					throw new IllegalStateException(e);
				}
				break;
			case DATABASE:
				gidFiles = new GidController( ).getGidFileDbGidFiles(gid);
				break;
			default:
				throw new IllegalArgumentException(
						source + " is not a supported enum value for DateTools$getAvailableYears(...)");
		}
		return gidFiles;
	}
	
}
