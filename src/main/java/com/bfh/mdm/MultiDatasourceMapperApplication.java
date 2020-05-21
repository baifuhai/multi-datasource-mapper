package com.bfh.mdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;

@EnableAutoConfiguration(exclude = TransactionAutoConfiguration.class)
@SpringBootApplication
public class MultiDatasourceMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDatasourceMapperApplication.class, args);
	}

}
