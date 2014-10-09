package com.npm.mmdb.mybatis.dao;

import com.npm.mmdb.mybatis.model.Gid;
import com.npm.mmdb.mybatis.model.GidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GidMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int countByExample(GidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int deleteByExample(GidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int insert(Gid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int insertSelective(Gid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    List<Gid> selectByExample(GidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    Gid selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int updateByExampleSelective(@Param("record") Gid record, @Param("example") GidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int updateByExample(@Param("record") Gid record, @Param("example") GidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int updateByPrimaryKeySelective(Gid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gid
     *
     * @mbggenerated Thu Oct 09 08:43:23 EDT 2014
     */
    int updateByPrimaryKey(Gid record);
}