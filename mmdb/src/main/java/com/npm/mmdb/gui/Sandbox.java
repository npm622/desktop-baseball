package com.npm.mmdb.gui;


import com.npm.mmdb.mybatis.dao.DbQuery;
import com.npm.mmdb.mybatis.dao.gid.GidMapper;
import com.npm.mmdb.mybatis.model.gid.Gid;
import com.npm.mmdb.mybatis.model.gid.Gid.GidBuilder;
import com.npm.mmdb.mybatis.model.gid.GidExample;


public class Sandbox
{
	public static void main(final String[ ] args)
	{
		DbQuery<GidMapper, Gid, Integer, GidExample> test = new DbQuery<>(GidMapper.class);
		// GidExample example = new GidExample( );
		// example.or( ).andYearEqualTo(new Short((short) 2014)).andMonthEqualTo(new Byte((byte) 6))
		// .andDayEqualTo(new Byte((byte) 22));
		Gid gid = new GidBuilder( ).year(3333).month(6).day(22).awayAbbrev("npm").homeAbbrev("mjm").build( );
		test.insert(gid);
		System.out.println(gid.getId( ));
	}
}
