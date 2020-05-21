package com.bfh.mdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Map;

@Component
public class AppListener implements ApplicationListener<ApplicationEvent> {

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof ContextRefreshedEvent) {
			Map<String, PlatformTransactionManager> beansOfType = applicationContext.getBeansOfType(PlatformTransactionManager.class);
			Map<String, TransactionInterceptor> beansOfType2 = applicationContext.getBeansOfType(TransactionInterceptor.class);
			System.out.println(beansOfType);
			System.out.println(beansOfType2);
		}
	}

}
