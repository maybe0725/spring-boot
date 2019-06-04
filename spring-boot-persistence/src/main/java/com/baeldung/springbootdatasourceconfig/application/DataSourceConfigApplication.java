package com.baeldung.springbootdatasourceconfig.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.baeldung.springbootdatasourceconfig.application.entities.User;
import com.baeldung.springbootdatasourceconfig.application.repositories.UserRepository;

@SpringBootApplication
public class DataSourceConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSourceConfigApplication.class, args);
	}

	@Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        return (String[] args) -> {
            User user1 = new User("John", "john@domain.com");
            User user2 = new User("Julie", "julie@domain.com");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.findAll().forEach(user -> System.out.println(user.getName()));
        };
    }
}
