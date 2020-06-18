package com.cyx.accountserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 内部服务,提供用户账户服务,包含账户创建,充值,扣款
 * 数据库位于本地,locahost:3306/localdb
 */
@SpringBootApplication(scanBasePackages = { "com.cyx.accountserver" })
@EnableJpaRepositories(basePackages = "com.cyx.accountserver.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "jtaTransactionManager")
@EntityScan(basePackages = { "com.cyx.accountserver.model" })
public class AccountServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServerApplication.class, args);
	}

	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager){
		System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
		return new Object();
	}

}
