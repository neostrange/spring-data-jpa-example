package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EntityScan(basePackages = {"com.example.model"})
@EnableJpaRepositories(basePackages = {"com.example.repository"})
@EnableTransactionManagement
@ImportResource("classpath:/spring/tfeeds-db-endpoint.xml")
@EnableScheduling
public class TfeedsDbEndpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfeedsDbEndpointApplication.class, args);
	}

}
