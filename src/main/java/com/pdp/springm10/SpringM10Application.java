package com.pdp.springm10;

import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
@OpenAPI30
@EnableWebSecurity
public class SpringM10Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringM10Application.class, args);
    }
}
