package com.afkl.travel.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.afkl.travel.exercise.Consumer;
import com.afkl.travel.exercise.clientkit.ApiException;
import com.afkl.travel.exercise.clientkit.model.Location;

@Controller
public class AirportController {
	@Autowired
	private Consumer consumer;

	// @SuppressWarnings("unchecked")
	@GetMapping("/airports/{country}")
	ResponseEntity<List<Location>> one(@PathVariable String country,
			@RequestHeader(value = "accept-language", required = false) String acceptLanguage) throws ApiException {
		return new ResponseEntity(consumer.printListofALLAirports(country, acceptLanguage), HttpStatus.OK);

	}
}
