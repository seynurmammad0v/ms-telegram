package com.ms.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramApplication.class, args);
	}

}
