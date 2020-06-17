package com.apiit.onceuponabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.apiit.onceuponabook.models")
@EnableJpaRepositories("com.apiit.onceuponabook.repositories")
@EnableJpaAuditing
public class OnceUponABookApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnceUponABookApplication.class, args);
    }

}
