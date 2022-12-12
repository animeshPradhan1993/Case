package com.afkl.travel.exercise;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.afkl.travel.exercise.clientkit.ApiException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws BeansException, ApiException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		//context.getBean(Consumer.class).printListofALLAirportsInUSA();
	}

}
