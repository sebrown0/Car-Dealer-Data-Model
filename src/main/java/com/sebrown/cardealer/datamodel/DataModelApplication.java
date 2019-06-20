package com.sebrown.cardealer.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sebrown.cardealer.datamodel.repository.EmployeeRepositoryHelper;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
        )
@EnableJpaAuditing
@EnableAutoConfiguration(exclude = {
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
	})
public class DataModelApplication implements CommandLineRunner{
	
	@Autowired
	EmployeeRepositoryHelper empRepo;

	public static void main(String[] args) {
		SpringApplication.run(DataModelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
