package com.suraj.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO) // Add this line
public class EcommeceSportsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommeceSportsStoreApplication.class, args);
	}

}
