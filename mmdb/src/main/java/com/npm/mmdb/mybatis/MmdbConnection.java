package com.npm.mmdb.mybatis;


import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MmdbConnection
{
	public static final SqlSessionFactory	SQL_SESSION_FACTORY;
	static
	{
		String resource = "com/mmdb/mybatis/core/mybatis-config.xml";
		InputStream inputStream;
		try
		{
			inputStream = Resources.getResourceAsStream(resource);
		}
		catch (IOException e)
		{
			throw new IllegalStateException(e);
		}
		SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder( ).build(inputStream);
	}
}
