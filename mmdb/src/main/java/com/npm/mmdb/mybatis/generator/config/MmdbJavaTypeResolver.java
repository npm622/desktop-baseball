package com.npm.mmdb.mybatis.generator.config;


import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;


public class MmdbJavaTypeResolver extends JavaTypeResolverDefaultImpl
{
	public MmdbJavaTypeResolver( )
	{
		properties = new Properties( );
		typeMap = new HashMap<>( );
		
		typeMap.put(Integer.valueOf(Types.ARRAY), new JdbcTypeInformation("ARRAY", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.BIGINT), new JdbcTypeInformation("BIGINT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Long.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.BINARY), new JdbcTypeInformation("BINARY", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Integer.valueOf(Types.BIT), new JdbcTypeInformation("BIT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Boolean.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.BLOB), new JdbcTypeInformation("BLOB", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Integer.valueOf(Types.BOOLEAN), new JdbcTypeInformation("BOOLEAN", //$NON-NLS-1$
				new FullyQualifiedJavaType(Boolean.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.CHAR), new JdbcTypeInformation("CHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.CLOB), new JdbcTypeInformation("CLOB", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.DATALINK), new JdbcTypeInformation("DATALINK", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.DATE), new JdbcTypeInformation("DATE", //$NON-NLS-1$
				new FullyQualifiedJavaType(Date.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.DISTINCT), new JdbcTypeInformation("DISTINCT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.DOUBLE), new JdbcTypeInformation("DOUBLE", //$NON-NLS-1$
				new FullyQualifiedJavaType(Double.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.FLOAT), new JdbcTypeInformation("FLOAT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Double.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.INTEGER), new JdbcTypeInformation("INTEGER", //$NON-NLS-1$
				new FullyQualifiedJavaType(Integer.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.JAVA_OBJECT), new JdbcTypeInformation("JAVA_OBJECT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.LONGVARBINARY), new JdbcTypeInformation("LONGVARBINARY", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Integer.valueOf(Types.LONGVARCHAR), new JdbcTypeInformation("LONGVARCHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.NCHAR), new JdbcTypeInformation("NCHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.NCLOB), new JdbcTypeInformation("NCLOB", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.NVARCHAR), new JdbcTypeInformation("NVARCHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.NULL), new JdbcTypeInformation("NULL", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.OTHER), new JdbcTypeInformation("OTHER", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.REAL), new JdbcTypeInformation("REAL", //$NON-NLS-1$
				new FullyQualifiedJavaType(Float.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.REF), new JdbcTypeInformation("REF", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.SMALLINT), new JdbcTypeInformation("SMALLINT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Integer.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.STRUCT), new JdbcTypeInformation("STRUCT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Object.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.TIME), new JdbcTypeInformation("TIME", //$NON-NLS-1$
				new FullyQualifiedJavaType(Date.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.TIMESTAMP), new JdbcTypeInformation("TIMESTAMP", //$NON-NLS-1$
				new FullyQualifiedJavaType(Date.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.TINYINT), new JdbcTypeInformation("TINYINT", //$NON-NLS-1$
				new FullyQualifiedJavaType(Integer.class.getName( ))));
		typeMap.put(Integer.valueOf(Types.VARBINARY), new JdbcTypeInformation("VARBINARY", //$NON-NLS-1$
				new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
		typeMap.put(Integer.valueOf(Types.VARCHAR), new JdbcTypeInformation("VARCHAR", //$NON-NLS-1$
				new FullyQualifiedJavaType(String.class.getName( ))));
	}
	
	@Override
	public FullyQualifiedJavaType calculateJavaType(final IntrospectedColumn introspectedColumn)
	{
		// Calculates and returns the Java type that should be associated with this column based on the jdbc type,
		// length, and scale of the column.
		FullyQualifiedJavaType answer;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(Integer.valueOf(introspectedColumn.getJdbcType( )));
		if (jdbcTypeInformation == null)
		{
			switch (introspectedColumn.getJdbcType( ))
			{
				case Types.DECIMAL:
				case Types.NUMERIC:
					if (introspectedColumn.getScale( ) > 0 || introspectedColumn.getLength( ) > 18 || forceBigDecimals)
					{
						answer = new FullyQualifiedJavaType(BigDecimal.class.getName( ));
					}
					else if (introspectedColumn.getLength( ) > 9)
					{
						answer = new FullyQualifiedJavaType(Long.class.getName( ));
					}
					else
					{
						answer = new FullyQualifiedJavaType(Integer.class.getName( ));
					}
					break;
				default:
					answer = null;
					break;
			}
		}
		else
		{
			answer = jdbcTypeInformation.getFullyQualifiedJavaType( );
		}
		return answer;
	}
}
