package com.frankarrot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FrankarrotApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrankarrotApplication.class, args);
    }

}
