package com.npm.mmdb.mybatis.model.gid;

import java.util.ArrayList;
import java.util.List;

public class GidFileExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	protected String	orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	protected boolean	distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	protected List<Criteria>	oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public GidFileExample( )
	{
		oredCriteria = new ArrayList<Criteria>( );
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public void setOrderByClause(String orderByClause)
	{
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public String getOrderByClause( )
	{
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public void setDistinct(boolean distinct)
	{
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public boolean isDistinct( )
	{
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public List<Criteria> getOredCriteria( )
	{
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public void or(Criteria criteria)
	{
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public Criteria or( )
	{
		Criteria criteria = createCriteriaInternal( );
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public Criteria createCriteria( )
	{
		Criteria criteria = createCriteriaInternal( );
		if (oredCriteria.size( ) == 0)
		{
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	protected Criteria createCriteriaInternal( )
	{
		Criteria criteria = new Criteria( );
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public void clear( )
	{
		oredCriteria.clear( );
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	protected abstract static class GeneratedCriteria
	{
		protected List<Criterion>	criteria;
		
		protected GeneratedCriteria( )
		{
			super( );
			criteria = new ArrayList<Criterion>( );
		}
		
		public boolean isValid( )
		{
			return criteria.size( ) > 0;
		}
		
		public List<Criterion> getAllCriteria( )
		{
			return criteria;
		}
		
		public List<Criterion> getCriteria( )
		{
			return criteria;
		}
		
		protected void addCriterion(String condition)
		{
			if (condition == null)
			{
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}
		
		protected void addCriterion(String condition, Object value, String property)
		{
			if (value == null)
			{
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}
		
		protected void addCriterion(String condition, Object value1, Object value2, String property)
		{
			if (value1 == null || value2 == null)
			{
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}
		
		public Criteria andIdIsNull( )
		{
			addCriterion("id is null");
			return (Criteria) this;
		}
		
		public Criteria andIdIsNotNull( )
		{
			addCriterion("id is not null");
			return (Criteria) this;
		}
		
		public Criteria andIdEqualTo(Integer value)
		{
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotEqualTo(Integer value)
		{
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdGreaterThan(Integer value)
		{
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdGreaterThanOrEqualTo(Integer value)
		{
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdLessThan(Integer value)
		{
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdLessThanOrEqualTo(Integer value)
		{
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdIn(List<Integer> values)
		{
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotIn(List<Integer> values)
		{
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdBetween(Integer value1, Integer value2)
		{
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotBetween(Integer value1, Integer value2)
		{
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}
		
		public Criteria andFilenameIsNull( )
		{
			addCriterion("filename is null");
			return (Criteria) this;
		}
		
		public Criteria andFilenameIsNotNull( )
		{
			addCriterion("filename is not null");
			return (Criteria) this;
		}
		
		public Criteria andFilenameEqualTo(String value)
		{
			addCriterion("filename =", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameNotEqualTo(String value)
		{
			addCriterion("filename <>", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameGreaterThan(String value)
		{
			addCriterion("filename >", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameGreaterThanOrEqualTo(String value)
		{
			addCriterion("filename >=", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameLessThan(String value)
		{
			addCriterion("filename <", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameLessThanOrEqualTo(String value)
		{
			addCriterion("filename <=", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameLike(String value)
		{
			addCriterion("filename like", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameNotLike(String value)
		{
			addCriterion("filename not like", value, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameIn(List<String> values)
		{
			addCriterion("filename in", values, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameNotIn(List<String> values)
		{
			addCriterion("filename not in", values, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameBetween(String value1, String value2)
		{
			addCriterion("filename between", value1, value2, "filename");
			return (Criteria) this;
		}
		
		public Criteria andFilenameNotBetween(String value1, String value2)
		{
			addCriterion("filename not between", value1, value2, "filename");
			return (Criteria) this;
		}
		
		public Criteria andExtIsNull( )
		{
			addCriterion("ext is null");
			return (Criteria) this;
		}
		
		public Criteria andExtIsNotNull( )
		{
			addCriterion("ext is not null");
			return (Criteria) this;
		}
		
		public Criteria andExtEqualTo(String value)
		{
			addCriterion("ext =", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtNotEqualTo(String value)
		{
			addCriterion("ext <>", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtGreaterThan(String value)
		{
			addCriterion("ext >", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtGreaterThanOrEqualTo(String value)
		{
			addCriterion("ext >=", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtLessThan(String value)
		{
			addCriterion("ext <", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtLessThanOrEqualTo(String value)
		{
			addCriterion("ext <=", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtLike(String value)
		{
			addCriterion("ext like", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtNotLike(String value)
		{
			addCriterion("ext not like", value, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtIn(List<String> values)
		{
			addCriterion("ext in", values, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtNotIn(List<String> values)
		{
			addCriterion("ext not in", values, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtBetween(String value1, String value2)
		{
			addCriterion("ext between", value1, value2, "ext");
			return (Criteria) this;
		}
		
		public Criteria andExtNotBetween(String value1, String value2)
		{
			addCriterion("ext not between", value1, value2, "ext");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathIsNull( )
		{
			addCriterion("local_path is null");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathIsNotNull( )
		{
			addCriterion("local_path is not null");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathEqualTo(String value)
		{
			addCriterion("local_path =", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathNotEqualTo(String value)
		{
			addCriterion("local_path <>", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathGreaterThan(String value)
		{
			addCriterion("local_path >", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathGreaterThanOrEqualTo(String value)
		{
			addCriterion("local_path >=", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathLessThan(String value)
		{
			addCriterion("local_path <", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathLessThanOrEqualTo(String value)
		{
			addCriterion("local_path <=", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathLike(String value)
		{
			addCriterion("local_path like", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathNotLike(String value)
		{
			addCriterion("local_path not like", value, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathIn(List<String> values)
		{
			addCriterion("local_path in", values, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathNotIn(List<String> values)
		{
			addCriterion("local_path not in", values, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathBetween(String value1, String value2)
		{
			addCriterion("local_path between", value1, value2, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andLocalPathNotBetween(String value1, String value2)
		{
			addCriterion("local_path not between", value1, value2, "localPath");
			return (Criteria) this;
		}
		
		public Criteria andIdGidIsNull( )
		{
			addCriterion("id_gid is null");
			return (Criteria) this;
		}
		
		public Criteria andIdGidIsNotNull( )
		{
			addCriterion("id_gid is not null");
			return (Criteria) this;
		}
		
		public Criteria andIdGidEqualTo(Integer value)
		{
			addCriterion("id_gid =", value, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidNotEqualTo(Integer value)
		{
			addCriterion("id_gid <>", value, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidGreaterThan(Integer value)
		{
			addCriterion("id_gid >", value, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidGreaterThanOrEqualTo(Integer value)
		{
			addCriterion("id_gid >=", value, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidLessThan(Integer value)
		{
			addCriterion("id_gid <", value, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidLessThanOrEqualTo(Integer value)
		{
			addCriterion("id_gid <=", value, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidIn(List<Integer> values)
		{
			addCriterion("id_gid in", values, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidNotIn(List<Integer> values)
		{
			addCriterion("id_gid not in", values, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidBetween(Integer value1, Integer value2)
		{
			addCriterion("id_gid between", value1, value2, "idGid");
			return (Criteria) this;
		}
		
		public Criteria andIdGidNotBetween(Integer value1, Integer value2)
		{
			addCriterion("id_gid not between", value1, value2, "idGid");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gid_file
	 * @mbggenerated  Mon Oct 13 19:19:57 EDT 2014
	 */
	public static class Criterion
	{
		private String	condition;
		private Object	value;
		private Object	secondValue;
		private boolean	noValue;
		private boolean	singleValue;
		private boolean	betweenValue;
		private boolean	listValue;
		private String	typeHandler;
		
		public String getCondition( )
		{
			return condition;
		}
		
		public Object getValue( )
		{
			return value;
		}
		
		public Object getSecondValue( )
		{
			return secondValue;
		}
		
		public boolean isNoValue( )
		{
			return noValue;
		}
		
		public boolean isSingleValue( )
		{
			return singleValue;
		}
		
		public boolean isBetweenValue( )
		{
			return betweenValue;
		}
		
		public boolean isListValue( )
		{
			return listValue;
		}
		
		public String getTypeHandler( )
		{
			return typeHandler;
		}
		
		protected Criterion(String condition)
		{
			super( );
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}
		
		protected Criterion(String condition, Object value, String typeHandler)
		{
			super( );
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List< ? >)
			{
				this.listValue = true;
			}
			else
			{
				this.singleValue = true;
			}
		}
		
		protected Criterion(String condition, Object value)
		{
			this(condition, value, null);
		}
		
		protected Criterion(String condition, Object value, Object secondValue, String typeHandler)
		{
			super( );
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}
		
		protected Criterion(String condition, Object value, Object secondValue)
		{
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table gid_file
     *
     * @mbggenerated do_not_delete_during_merge Mon Oct 13 19:15:58 EDT 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}