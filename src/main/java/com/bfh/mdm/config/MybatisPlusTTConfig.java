package com.bfh.mdm.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(basePackages = "com.bfh.mdm.mapper.tt", sqlSessionFactoryRef = "sqlSessionFactoryTT")
@Configuration
public class MybatisPlusTTConfig {

	@Autowired
	@Qualifier("dataSourceTT")
	DataSource dataSourceTT;

	@Autowired
	PaginationInterceptor paginationInterceptor;

	@Bean
	public DataSourceTransactionManager transactionManagerTT() {
		return new DataSourceTransactionManager(dataSourceTT);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryTT() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();

		sqlSessionFactory.setDataSource(dataSourceTT);

		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/tt/*.xml"));
		sqlSessionFactory.setTypeAliasesPackage("com.bfh.mdm.entity.tt");

		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setCacheEnabled(false);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		sqlSessionFactory.setConfiguration(configuration);

		sqlSessionFactory.setPlugins(paginationInterceptor);

		GlobalConfig globalConfig = new GlobalConfig();
		GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
		dbConfig.setTablePrefix("t_");
		dbConfig.setTableUnderline(true);
		dbConfig.setIdType(IdType.AUTO);
		dbConfig.setLogicDeleteValue("1");
		dbConfig.setLogicNotDeleteValue("0");
		globalConfig.setDbConfig(dbConfig);
		sqlSessionFactory.setGlobalConfig(globalConfig);

		return sqlSessionFactory.getObject();
	}

}
