package com.word.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WordcollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordcollectionApplication.class, args);
	}

}
