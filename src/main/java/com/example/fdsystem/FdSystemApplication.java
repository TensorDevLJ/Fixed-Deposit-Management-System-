package com.example.fdsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FdSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdSystemApplication.class, args);
	}

}
