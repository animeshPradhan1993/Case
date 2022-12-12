package com.afkl.travel.exercise.Converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.afkl.travel.exercise.model.LocationType;

@Component
public class CustomConverterLocation implements Converter<String, LocationType> {

	@Override
	public LocationType convert(String type) {
		try {
			return LocationType.fromValue(type);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
