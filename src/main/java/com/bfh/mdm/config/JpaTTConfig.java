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
		basePackages = "com.bfh.mdm.repository.tt",
		entityManagerFactoryRef = "entityManagerFactoryTT",
		transactionManagerRef = "transactionManagerTTJpa"
)
@Configuration
public class JpaTTConfig {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("dataSourceTT")
	DataSource dataSourceTT;

	@Bean
	public PlatformTransactionManager transactionManagerTTJpa() {
		return new JpaTransactionManager(entityManagerFactoryTT().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryTT() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSourceTT);

		entityManagerFactoryBean.setPackagesToScan("com.bfh.mdm.entity.tt");

		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		jpaProperties.put("hibernate.implicit_naming_strategy", env.getProperty("spring.jpa.hibernate.naming.implicit-strategy"));
		jpaProperties.put("hibernate.physical_naming_strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		entityManagerFactoryBean.setPersistenceUnitName("persistenceUnitNameTT");

		return entityManagerFactoryBean;
	}

}
