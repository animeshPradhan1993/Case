package com.afkl.travel.exercise.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.afkl.travel.exercise.Exception.ResourceNotFoundException;
import com.afkl.travel.exercise.api.LocationsApi;
import com.afkl.travel.exercise.dto.LocationDTO;
import com.afkl.travel.exercise.mapper.mapLocationDTOtoLocation;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.service.LocationService;

import brave.Tracer;

@Controller
public class LocationController implements LocationsApi {
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	@Autowired
	private LocationService service;
	@Value("${case.default.language}")
	private String defaultLanguage;

	private mapLocationDTOtoLocation mapper = new mapLocationDTOtoLocation();
	@Autowired
	private Tracer tracer;

	@Override
	public ResponseEntity<Location> getLocationByTypeAndCode(LocationType type, String code, String acceptLanguage) {
		logger.info("Trace Id {} received request to retrieve location by type: {} and code: {}",
				tracer.currentSpan().context().traceIdString(), type, code);
		acceptLanguage = StringUtils.isEmpty(acceptLanguage) ? defaultLanguage : acceptLanguage;
		if (type == null) {
			throw new ResourceNotFoundException();
		}
		LocationDTO dto2 = (LocationDTO) service.getLocationByTypeAndCode(code, acceptLanguage, type.getValue())
				.orElseThrow(() -> new ResourceNotFoundException());

		Location l1 = mapper.map(dto2);
		return new ResponseEntity<>(l1, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Location>> getLocations(String acceptLanguage) {
		logger.info("Trace Id {} received request to retrieve all locations",
				tracer.currentSpan().context().traceIdString());
		acceptLanguage = StringUtils.isEmpty(acceptLanguage) ? defaultLanguage : acceptLanguage;
		Optional<List<LocationDTO>> opt = service.findAllLocationByLanguage(acceptLanguage);
		List<LocationDTO> dto2 = opt.isPresent() ? (List<LocationDTO>) opt.get() : new ArrayList<>();
		List<Location> l1 = new ArrayList<>();
		for (LocationDTO dto : dto2) {
			l1.add(mapper.map(dto));
		}
		return new ResponseEntity<>(l1, HttpStatus.OK);
	}

}
