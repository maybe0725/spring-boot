package com.baeldung.disabledatasource.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class DisableDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisableDataSourceApplication.class, args);
    }
}
