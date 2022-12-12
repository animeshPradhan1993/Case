package com.afkl.travel.exercise.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.afkl.travel.exercise.AirportPrintRunner;
import com.afkl.travel.exercise.Consumer;
import com.afkl.travel.exercise.clientkit.ApiClient;
import com.afkl.travel.exercise.clientkit.api.LocationsApi;

@Configuration
public class Config {

	@Bean
	public ApiClient client() {

		return new ApiClient();
	}

	@Bean
	public LocationsApi api() {
		return new LocationsApi();
	}

	@Bean
	public Consumer consumer() {
		return new Consumer();
	}

	@Bean
	public AirportPrintRunner runner() {
		return new AirportPrintRunner();
	}

}
