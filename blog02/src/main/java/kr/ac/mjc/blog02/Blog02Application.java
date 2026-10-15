package kr.ac.mjc.blog02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Blog02Application {

	public static void main(String[] args) {
		SpringApplication.run(Blog02Application.class, args);
	}

}
