package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.config.GlobalProperties;
import com.example.config.WordPressProperties;

/**
 * https://www.mkyong.com/spring-boot/spring-boot-yaml-example/
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private WordPressProperties wpProperties;

    @Autowired
    private GlobalProperties globalProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
    	System.out.println("\n==================================================");
        System.out.println(globalProperties);
        System.out.println("----------------------------------------------------");
        System.out.println(wpProperties);
        System.out.println("==================================================\n");
    }
}
