package com.npm.mmdb.mybatis.dao.gid;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.npm.mmdb.mybatis.dao.RootDao;
import com.npm.mmdb.mybatis.model.gid.GidFile;
import com.npm.mmdb.mybatis.model.gid.GidFileExample;


public interface GidFileMapper extends RootDao<GidFile, Integer, GidFileExample>
{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int countByExample(GidFileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int deleteByExample(GidFileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int insert(GidFile record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int insertSelective(GidFile record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	List<GidFile> selectByExample(GidFileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	GidFile selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int updateByExampleSelective(@Param("record") GidFile record, @Param("example") GidFileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int updateByExample(@Param("record") GidFile record, @Param("example") GidFileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int updateByPrimaryKeySelective(GidFile record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	@Override
	int updateByPrimaryKey(GidFile record);
}