package com.sebrown.cardealer.datamodel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
        )
@EnableJpaAuditing
@EnableAutoConfiguration(exclude = {
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
	})

@EntityScan(basePackages = {"com.sebrown.cardealer.datamodel.model"} )
@EnableJpaRepositories(basePackages = {"com.sebrown.cardealer.datamodel.repository"})
public class DataModelApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(DataModelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
