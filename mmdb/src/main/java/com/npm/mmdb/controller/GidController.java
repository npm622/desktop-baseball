package com.npm.mmdb.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.npm.mmdb.mybatis.dao.DbQuery;
import com.npm.mmdb.mybatis.dao.gid.GidFileMapper;
import com.npm.mmdb.mybatis.dao.gid.GidMapper;
import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.GidExample;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.mybatis.model.gid.GidFileExample;

public class GidController
{
	private DbQuery<GidMapper, Gid, Integer, GidExample>				gidDbQuery;
	private DbQuery<GidFileMapper, GidFile, Integer, GidFileExample>	gidFileDbQuery;
	
	public GidController( )
	{
		gidDbQuery = new DbQuery<>(GidMapper.class);
		gidFileDbQuery = new DbQuery<>(GidFileMapper.class);
	}
	
	public final List<Integer> getGidDbYears( )
	{
		Set<Integer> yearSet = new HashSet<>( );
		for (Gid gid : gidDbQuery.selectByExample(null))
		{
			yearSet.add(gid.getYear( ));
		}
		List<Integer> years = new ArrayList<>( );
		for (Integer year : yearSet)
		{
			years.add(year);
		}
		Collections.sort(years);
		return years;
	}
	
	public final List<Integer> getGidDbMonths(final Integer year)
	{
		Set<Integer> monthSet = new HashSet<>( );
		GidExample gidExample = new GidExample( );
		gidExample.or( ).andYearEqualTo(year);
		for (Gid gid : gidDbQuery.selectByExample(gidExample))
		{
			monthSet.add(gid.getMonth( ));
		}
		List<Integer> months = new ArrayList<>( );
		for (Integer month : monthSet)
		{
			months.add(month);
		}
		Collections.sort(months);
		return months;
	}
	
	public final List<Integer> getGidDbDays(final Integer year, final Integer month)
	{
		Set<Integer> daySet = new HashSet<>( );
		GidExample gidExample = new GidExample( );
		gidExample.or( ).andYearEqualTo(year).andMonthEqualTo(month);
		for (Gid gid : gidDbQuery.selectByExample(gidExample))
		{
			daySet.add(gid.getDay( ));
		}
		List<Integer> days = new ArrayList<>( );
		for (Integer day : daySet)
		{
			days.add(day);
		}
		Collections.sort(days);
		return days;
	}
	
	public final List<Gid> getGidDbGids(final Integer year, final Integer month, final Integer day)
	{
		List<Gid> gids = new ArrayList<>( );
		GidExample gidExample = new GidExample( );
		gidExample.or( ).andYearEqualTo(year).andMonthEqualTo(month).andDayEqualTo(day);
		gids = gidDbQuery.selectByExample(gidExample);
		return gids;
	}
	
	public final List<GidFile> getGidFileDbGidFiles(final Gid gid)
	{
		List<GidFile> gidFiles = new ArrayList<>( );
		GidFileExample gidFileExample = new GidFileExample( );
		gidFileExample.or( ).andIdGidEqualTo(gid.getId( ));
		gidFiles = gidFileDbQuery.selectByExample(gidFileExample);
		return gidFiles;
	}
}
