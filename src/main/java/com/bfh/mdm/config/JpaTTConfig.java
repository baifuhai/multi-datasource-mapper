package com.bfh.mdm.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
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
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.CompositeTransactionAttributeSource;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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

	@Bean
	public PlatformTransactionManager transactionManagerTTJpa() {
		return new JpaTransactionManager(entityManagerFactoryTT().getObject());
	}

	/*
	@Bean
	public TransactionInterceptor txAdviceTTJpa() {
		NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();

		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		readOnlyTx.setReadOnly(true);

		RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
		requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));

		Map<String, TransactionAttribute> txAttributeMap = new HashMap<>();
		txAttributeMap.put("*", requiredTx);
		txAttributeMap.put("get*", readOnlyTx);
		txAttributeMap.put("find*", readOnlyTx);
		txAttributeMap.put("query*", readOnlyTx);
		txAttributeMap.put("count*", readOnlyTx);

		txAttributeSource.setNameMap(txAttributeMap);

		CompositeTransactionAttributeSource compositeTransactionAttributeSource =
				new CompositeTransactionAttributeSource(new AnnotationTransactionAttributeSource(), txAttributeSource);

		return new TransactionInterceptor(transactionManagerTTJpa(), compositeTransactionAttributeSource);
	}

	@Bean
	public Advisor txAdviceAdvisorTTJpa() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.bfh.mdm.service.tt.*.*(..))");

		return new DefaultPointcutAdvisor(pointcut, txAdviceTTJpa());
	}
	*/

}
