package com.cyx.goodsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 内部服务,提供商品服务
 * 数据库位于远程,212.64.4.189:3306/clouddb
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cyx.goodsserver.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "jtaTransactionManager")
@EntityScan(basePackages = { "com.cyx.goodsserver.model" })
public class GoodsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsServerApplication.class, args);
	}

}
