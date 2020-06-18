package com.cyx.orderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 订单服务,对外接口
 * 需要创建订单,并调用账户服务和商品服务
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cyx.orderserver.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "jtaTransactionManager")
@EntityScan(basePackages = { "com.cyx.orderserver.model" })
public class OrderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServerApplication.class, args);
	}

}
