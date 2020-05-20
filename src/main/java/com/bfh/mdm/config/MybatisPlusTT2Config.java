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

@MapperScan(basePackages = "com.bfh.mdm.mapper.tt2", sqlSessionFactoryRef = "sqlSessionFactoryTT2")
@Configuration
public class MybatisPlusTT2Config {

	@Autowired
	@Qualifier("dataSourceTT2")
	DataSource dataSourceTT2;

	@Autowired
	PaginationInterceptor paginationInterceptor;

	@Bean
	public DataSourceTransactionManager transactionManagerTT2() {
		return new DataSourceTransactionManager(dataSourceTT2);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryTT2() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();

		sqlSessionFactory.setDataSource(dataSourceTT2);

		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/tt2/*.xml"));
		sqlSessionFactory.setTypeAliasesPackage("com.bfh.mdm.entity.tt2");

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
