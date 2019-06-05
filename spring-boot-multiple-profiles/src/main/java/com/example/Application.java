package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.config.ServerProperties;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ServerProperties serverProperties;

    @Override
    public void run(String... args) {
    	System.out.println("\n==================================================");
        System.out.println(serverProperties);
        System.out.println("==================================================\n");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
