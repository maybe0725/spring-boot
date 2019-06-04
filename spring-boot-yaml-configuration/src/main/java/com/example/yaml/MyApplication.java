package com.example.yaml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring YAML Configuration
 * https://www.baeldung.com/spring-yaml
 * https://github.com/eugenp/tutorials/tree/master/spring-core
 */
@SpringBootApplication
public class MyApplication implements CommandLineRunner {
 
    @Autowired
    private YAMLConfig myConfig;
 
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.run();
    }
 
    public void run(String... args) throws Exception {
    	System.out.println("\n==================================================");
        System.out.println("using environment: " + myConfig.getEnvironment());
        System.out.println("name: " + myConfig.getName());
        System.out.println("servers: " + myConfig.getServers());
        System.out.println("==================================================\n");
    }
}
