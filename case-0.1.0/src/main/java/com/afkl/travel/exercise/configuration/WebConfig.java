package com.afkl.travel.exercise.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.afkl.travel.exercise.Converters.CustomConverterLocation;

@Configuration

public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new CustomConverterLocation());
	}
}
