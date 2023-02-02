package com.crossasyst.manyTomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching
@SpringBootApplication
public class ManyTmanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyTmanyApplication.class, args);
	}

}
