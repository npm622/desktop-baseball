package com.npm.mmdb.mybatis.generator.config;


import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;


public class MmdbBuilderPlugin extends PluginAdapter
{
	public MmdbBuilderPlugin( )
	{
		super( );
	}

	@Override
	public boolean validate(final List<String> arg0)
	{
		return true;
	}
	
	@Override
	public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass,
			final IntrospectedTable introspectedTable)
	{
		generateBuilder(topLevelClass, introspectedTable);
		return true;
	}
	
	@Override
	public boolean modelPrimaryKeyClassGenerated(final TopLevelClass topLevelClass,
			final IntrospectedTable introspectedTable)
	{
		generateBuilder(topLevelClass, introspectedTable);
		return true;
	}
	
	@Override
	public boolean modelRecordWithBLOBsClassGenerated(final TopLevelClass topLevelClass,
			final IntrospectedTable introspectedTable)
	{
		generateBuilder(topLevelClass, introspectedTable);
		return true;
	}
	
	private final void generateBuilder(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable)
	{
		InnerClass builderClass = new InnerClass(topLevelClass.getType( ) + "Builder");
		context.getCommentGenerator( ).addClassComment(builderClass, introspectedTable);
		builderClass.setVisibility(JavaVisibility.PUBLIC);
		builderClass.setStatic(true);
		Method buildMethod = new Method("build");
		buildMethod.setVisibility(JavaVisibility.PUBLIC);
		buildMethod.setReturnType(topLevelClass.getType( ));
		StringBuilder buildBody = new StringBuilder("return new ").append(topLevelClass.getType( ).getShortName( ))
				.append("(");
		int counter = 0;
		for (Field field : topLevelClass.getFields( ))
		{
			Field f = new Field(field);
			f.setVisibility(JavaVisibility.PRIVATE);
			builderClass.addField(f);
			Method builderMethod = new Method(field.getName( ));
			builderMethod.setVisibility(JavaVisibility.PUBLIC);
			builderMethod.setReturnType(builderClass.getType( ));
			Parameter parameter = new Parameter(field.getType( ), "val", false);
			builderMethod.addParameter(parameter);
			StringBuilder methodBody = new StringBuilder( ).append(field.getName( )).append(" = ")
					.append(parameter.getName( )).append("; return this;");
			builderMethod.addBodyLine(methodBody.toString( ));
			builderClass.addMethod(builderMethod);
			if (counter++ != 0)
			{
				buildBody.append(", ");
			}
			buildBody.append(field.getName( ));
		}
		buildBody.append(");");
		buildMethod.addBodyLine(buildBody.toString( ));
		builderClass.addMethod(buildMethod);
		topLevelClass.addInnerClass(builderClass);
	}
}
