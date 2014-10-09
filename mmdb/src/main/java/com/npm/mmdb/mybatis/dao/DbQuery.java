package com.npm.mmdb.mybatis.dao;


import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.npm.mmdb.mybatis.MmdbConnection;


public class DbQuery<M extends MyBatisGeneratedDao<T, PK, E>, T, PK extends Serializable, E> implements MyBatisGeneratedDao<T, PK, E>
{
	private final Class<M>	clazz;
	
	public DbQuery(final Class<M> clazz)
	{
		this.clazz = clazz;
	}

	@Override
	public int countByExample(final E example)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).countByExample(example);
		}
		finally
		{
			session.close( );
		}
		return count;
	}
	
	@Override
	public int deleteByExample(final E example)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).deleteByExample(example);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public int deleteByPrimaryKey(final PK id)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).deleteByPrimaryKey(id);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public int insert(final T record)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).insert(record);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public int insertSelective(final T record)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).insertSelective(record);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public List<T> selectByExample(final E example)
	{
		List<T> results = null;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			results = session.getMapper(clazz).selectByExample(example);
		}
		finally
		{
			session.close( );
		}
		return results;
	}
	
	@Override
	public T selectByPrimaryKey(final PK id)
	{
		T result = null;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			result = session.getMapper(clazz).selectByPrimaryKey(id);
		}
		finally
		{
			session.close( );
		}
		return result;
	}
	
	@Override
	public int updateByExampleSelective(final T record, final E example)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).updateByExampleSelective(record, example);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public int updateByExample(final T record, final E example)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).updateByExample(record, example);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public int updateByPrimaryKeySelective(final T record)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).updateByPrimaryKeySelective(record);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
	@Override
	public int updateByPrimaryKey(final T record)
	{
		int count = 0;
		SqlSession session = MmdbConnection.SQL_SESSION_FACTORY.openSession( );
		try
		{
			count = session.getMapper(clazz).updateByPrimaryKey(record);
		}
		finally
		{
			session.commit( );
			session.close( );
		}
		return count;
	}
	
}
