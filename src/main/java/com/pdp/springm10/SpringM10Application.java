package com.pdp.springm10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class SpringM10Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringM10Application.class, args);
    }
}
