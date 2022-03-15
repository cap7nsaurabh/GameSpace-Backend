package com.game.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.game.space.Repository")
public class GameSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameSpaceApplication.class, args);
	}

}
