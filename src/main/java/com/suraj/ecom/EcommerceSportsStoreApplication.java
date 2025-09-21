package com.suraj.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO) // Add this line
public class EcommerceSportsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSportsStoreApplication.class, args);
	}

}
