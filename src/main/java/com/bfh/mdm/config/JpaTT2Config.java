package com.bfh.mdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(
		basePackages = "com.bfh.mdm.repository.tt2",
		entityManagerFactoryRef = "entityManagerFactoryTT2",
		transactionManagerRef = "transactionManagerTT2Jpa"
)
@Configuration
public class JpaTT2Config {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("dataSourceTT2")
	DataSource dataSourceTT2;

	@Bean
	public PlatformTransactionManager transactionManagerTT2Jpa() {
		return new JpaTransactionManager(entityManagerFactoryTT2().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryTT2() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSourceTT2);

		entityManagerFactoryBean.setPackagesToScan("com.bfh.mdm.entity.tt2");

		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		entityManagerFactoryBean.setPersistenceUnitName("persistenceUnitNameTT2");

		return entityManagerFactoryBean;
	}

}
