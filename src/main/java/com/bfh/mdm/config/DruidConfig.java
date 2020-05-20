package com.bfh.mdm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

	@ConfigurationProperties(prefix = "spring.datasource.tt")
	@Bean
	public DataSource dataSourceTT() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}

	@ConfigurationProperties(prefix = "spring.datasource.tt2")
	@Bean
	public DataSource dataSourceTT2() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}

}
