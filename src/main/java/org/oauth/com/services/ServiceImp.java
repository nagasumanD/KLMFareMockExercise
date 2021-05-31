package org.oauth.com.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.oauth.com.Dao.Dao;
import org.oauth.com.models.RequestStatisticsReq;
import org.oauth.com.models.RequestStatisticsRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServiceImp implements ServicesLayer{
	
	@Value("${url.getAirPortsList}")
	private String getAirportsurl;
	@Value("${url.getFareUrl}")
	private String getFareUrl;
	
	@Autowired()
	private WebClient webClient;
	
	@Autowired
	private Dao dao;
	
	@Override
	public String gerAriportsData() {
		return webClient.get()
                .uri(getAirportsurl)
                .retrieve()
                .bodyToMono(String.class).block();
	}
	@Override
	public String Fares(String orgin,String distination,String currency) {
		String url=getFareUrl.replace("origin_code", orgin);
		url=url.replace("destination_code", distination);
		
		return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class).block();
	}
	
	@Override
	public void requestdata(RequestStatisticsReq reqsts) throws FileNotFoundException, IOException{
		
		dao.requestdata(reqsts);
		
	}
	@Override
	public RequestStatisticsRes getStatistics() throws FileNotFoundException, IOException{
		
		return dao.getStatistics();
		
	}
}
