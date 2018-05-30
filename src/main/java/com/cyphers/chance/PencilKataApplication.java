package com.cyphers.chance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PencilKataApplication {

	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(PencilKataApplication.class);
        app.run(args);
	}

}
