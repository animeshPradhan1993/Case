package com.afkl.travel.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class AirportPrintRunner implements CommandLineRunner {
@Autowired
Consumer consumer;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		consumer.printListofALLAirports(null, null);
		
	}

}
