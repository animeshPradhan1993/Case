package com.afkl.travel.exercise.Converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.afkl.travel.exercise.model.LocationType;

@Converter
public class LocationConverter implements AttributeConverter<LocationType, String> {

	@Override
	public String convertToDatabaseColumn(LocationType attribute) {
		 if (attribute == null) {
	            return null;
	        }
	        return attribute.getValue();
	}

	@Override
	public LocationType convertToEntityAttribute(String dbData) {
		if(dbData==null) {
			return null;
		}
		 return LocationType.fromValue(dbData);
	}

}
