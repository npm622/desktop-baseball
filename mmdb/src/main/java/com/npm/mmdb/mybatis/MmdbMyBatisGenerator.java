package com.npm.mmdb.mybatis;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


public class MmdbMyBatisGenerator
{
	public static void main(final String[ ] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException
	{
		List<String> warnings = new ArrayList<>( );
		boolean overwrite = true;
		InputStream is = ClassLoader.class.getResourceAsStream("/com/npm/mmdb/mybatis/mybatis-generator-config.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
