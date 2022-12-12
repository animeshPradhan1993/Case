package com.afkl.travel.exercise.mapper;

import com.afkl.travel.exercise.dto.LocationDTO;
import com.afkl.travel.exercise.model.Location;

public class mapLocationDTOtoLocation {

	public Location map (LocationDTO locationDTO) {
		Location l1= new Location();
		l1.setCode(locationDTO.getCode());
		l1.setDescription(locationDTO.getTranslations().get(0).getDescription());
		l1.setLatitude(locationDTO.getLatitude());
		l1.setLongitude(locationDTO.getLongitude());
		l1.setName(locationDTO.getTranslations().get(0).getName());
		l1.setParentCode(locationDTO.getParentCode());
		l1.setParentType(locationDTO.getParentType());
		l1.setType(locationDTO.getType());
		return l1;
	}
}
