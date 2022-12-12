package com.afkl.travel.exercise;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.afkl.travel.exercise.dto.LocationDTO;
import com.afkl.travel.exercise.dto.TranslationDTO;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.service.LocationService;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LocationService service;
	LocationDTO location = new LocationDTO();
	TranslationDTO translation = new TranslationDTO();
	List<TranslationDTO> list = new ArrayList<>();

	private String username = "someuser";

	private String password = "psw@";
	String userpass = username + ":" + password;
	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));

	{
		list.add(translation);
		location.setCode("BBA");
		location.setTranslations(list);
		translation.setDescription("location.city.BBA.long (BBA)");
		location.setLatitude(-45.916389);
		location.setLongitude(-71.686944);
		location.setType(LocationType.CITY);
		location.setParentCode("CL");
		translation.setName("location.city.BBA.long");
		location.setParentType(LocationType.COUNTRY);

	}

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(service.getLocationByTypeAndCode(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(location));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", basicAuth);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/locations/city/BBA").headers(headers)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		assertEquals(result.getResponse().getStatus(), 200);
	}

	@Test
	public void retrieveDetailsForCourse2() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", basicAuth);
		Mockito.when(service.getLocationByTypeAndCode(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(location));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/locations/city/").headers(headers)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		assertEquals(result.getResponse().getStatus(), 404);
	}

	@Test
	public void retrieveDetailsForCourse3() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", basicAuth);
		Mockito.when(service.getLocationByTypeAndCode(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(location));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/locations/city23/BBA").headers(headers)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		assertEquals(result.getResponse().getStatus(), 404);
	}
}
