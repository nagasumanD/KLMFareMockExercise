package org.oauth.com.restControllers;

import org.oauth.com.services.ServicesLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class AirportsController {
	
	@Autowired
	private ServicesLayer service;
	
	@GetMapping("/airports")
	public Mono<String> getAriportsList(){
		return Mono.just(service.gerAriportsData());
	}

}
