package com.afkl.travel.exercise;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.afkl.travel.exercise.clientkit.ApiClient;
import com.afkl.travel.exercise.clientkit.ApiException;
import com.afkl.travel.exercise.clientkit.api.LocationsApi;
import com.afkl.travel.exercise.clientkit.model.Location;

public class Consumer {
	@Autowired
	private ApiClient client;
	@Autowired
	private LocationsApi api;
	@Value("${case.default.username}")
	private String username;
	@Value("${case.default.password}")
	private String password;
	@Value("${case-api.basePath}")
	private String basePath;
	@Value("${case.default.language}")
	private String language;
	private static final String USA_CODE = "US";

	public List<Location> printListofALLAirports(String country, String acceptLanguage) throws ApiException {
		// String userpass = username + ":" + password;

		String userpass = username + ":" + password;
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		client.setBasePath(basePath);
		acceptLanguage = StringUtils.isEmpty(acceptLanguage) ? language : acceptLanguage;
		final String filterCountry = StringUtils.isEmpty(country) ? USA_CODE : country;
		client.addDefaultHeader("accept-language", acceptLanguage);
		client.addDefaultHeader("Authorization", basicAuth);
		client.getAuthentication("BasicAuth");
		client.addDefaultHeader("Accept", "application/json");
		api.setApiClient(client);
		List<Location> allLocations = new ArrayList<>();
		allLocations = api.getLocations(acceptLanguage);
		final Set<String> cities;
		cities = allLocations.stream().filter(a -> a.getParentCode() != null && a.getParentCode().equals(filterCountry))
				.map(x -> x.getCode()).collect(Collectors.toSet());
		List<Location> airportsInUSA = new ArrayList<>();
		for (Location l : allLocations) {
			if (l.getParentCode() != null && cities.contains(l.getParentCode())) {
				airportsInUSA.add(l);
			}
		}

		airportsInUSA.forEach(x -> System.out.println(x));
		return airportsInUSA;
	}
}
