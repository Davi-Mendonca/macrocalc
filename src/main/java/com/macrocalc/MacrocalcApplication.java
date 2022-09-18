package com.macrocalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MacrocalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacrocalcApplication.class, args);
	}

}
