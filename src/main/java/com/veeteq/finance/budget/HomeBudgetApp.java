package com.veeteq.finance.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.veeteq.finance.budget.model")
@EnableJpaRepositories(basePackages = "com.veeteq.finance.budget.repository")
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.veeteq.finance.budget.integration")
public class HomeBudgetApp {

    public static void main(String[] args) {
        SpringApplication.run(HomeBudgetApp.class, args);
    }
}
