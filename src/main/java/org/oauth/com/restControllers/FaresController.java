package org.oauth.com.restControllers;

import org.oauth.com.services.ServicesLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class FaresController {
	
	@Autowired
	private ServicesLayer service;
	
	@GetMapping("/fare/{source}/{distination}")
	public Mono<String> offeredFare(@PathVariable("source") String source,@PathVariable("distination") String distination,
			@RequestParam(defaultValue="EUR") String currency){
		return Mono.just(service.Fares(source, distination, currency));
	}
	

}
