package com.devsuperiortask.movieflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class })
public class MovieflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieflixApplication.class, args);
	}

}
