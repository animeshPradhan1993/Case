package com.afkl.travel.exercise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afkl.travel.exercise.data.transfer.LocationRepository;
import com.afkl.travel.exercise.dto.LocationDTO;

@Service
public class LocationService {
	 @Autowired
     LocationRepository repository;

	 
	 public Optional<List<LocationDTO>> findAllLocationByLanguage(String language){
		 return repository.findByLanguage2(language);
		 
	 }
	 public Optional<LocationDTO> getLocationByTypeAndCode(String code, String language, String type) {
		 return repository.findByLocationByTypeAndCode(language, type, code);
	 }
}
