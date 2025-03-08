package me.developery.actuatorstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "me.**")
public class ActuatorStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorStudyApplication.class, args);
	}

}
