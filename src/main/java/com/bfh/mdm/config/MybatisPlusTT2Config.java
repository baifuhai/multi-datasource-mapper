package com.bfh.mdm.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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

@MapperScan(basePackages = "com.bfh.mdm.mapper.tt2", sqlSessionFactoryRef = "sqlSessionFactoryTT2")
@Configuration
public class MybatisPlusTT2Config {

	@Autowired
	@Qualifier("dataSourceTT2")
	DataSource dataSourceTT2;

	@Autowired
	PaginationInterceptor paginationInterceptor;

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
		configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
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

	@Bean
	public DataSourceTransactionManager transactionManagerTT2() {
		return new DataSourceTransactionManager(dataSourceTT2);
	}

	@Bean
	public TransactionInterceptor txAdviceTT2() {
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

		return new TransactionInterceptor(transactionManagerTT2(), compositeTransactionAttributeSource);
	}

	@Bean
	public Advisor txAdviceAdvisorTT2() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.bfh.mdm.service.tt2.*.*(..))");

		return new DefaultPointcutAdvisor(pointcut, txAdviceTT2());
	}

}
