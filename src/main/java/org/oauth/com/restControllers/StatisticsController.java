package org.oauth.com.restControllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.oauth.com.models.RequestStatisticsReq;
import org.oauth.com.models.RequestStatisticsRes;
import org.oauth.com.services.ServicesLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class StatisticsController {

	@Autowired
	private ServicesLayer service;
	
	@PostMapping("/logginResponseTime")
	public void logRequestResponse(@RequestBody RequestStatisticsReq log) throws FileNotFoundException, IOException {
		service.requestdata(log);
	}
	@GetMapping("/getLoggedData")
	public Mono<RequestStatisticsRes> getLoggedData() throws FileNotFoundException, IOException {
		return Mono.just(service.getStatistics());
	}
}
