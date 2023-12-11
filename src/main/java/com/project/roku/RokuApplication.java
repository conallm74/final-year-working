package com.project.roku;

import com.project.roku.medical_entities.Pharmacy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(RokuApplication.class, args);
	}

	@Bean
	Pharmacy getPharmacyName(){return new Pharmacy();}

}
