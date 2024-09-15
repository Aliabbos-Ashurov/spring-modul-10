package com.pdp.springm10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pdp")
public class SpringM10Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringM10Application.class, args);
    }
}
