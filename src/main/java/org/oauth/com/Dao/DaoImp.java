package org.oauth.com.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.oauth.com.models.RequestStatisticsReq;
import org.oauth.com.models.RequestStatisticsRes;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class DaoImp implements Dao{

	@Override
	public void requestdata(RequestStatisticsReq reqsts) throws FileNotFoundException, IOException {
		
		List<RequestStatisticsReq> logs =new ArrayList<RequestStatisticsReq>();
		ObjectMapper mapper=new ObjectMapper();
		try(InputStream inputStream=new FileInputStream(new File("logResponseData.json"))){
			TypeReference<List<RequestStatisticsReq>> typeref=new TypeReference<List<RequestStatisticsReq>>() {};
			if(inputStream.available() > 0) {
				logs= mapper.readValue(inputStream, typeref);
			}
		}
		logs.add(reqsts);
		mapper.writeValue(new File("logResponseData.json"), logs);
	}

	@Override
	public RequestStatisticsRes getStatistics() throws FileNotFoundException, IOException {
		int totalreq;
		long totalOkreq;
		long total4xxreq;
		long total500req;
		long sum;
		long averagetime;
		List<RequestStatisticsReq> total =new ArrayList<RequestStatisticsReq>();
		ObjectMapper mapper=new ObjectMapper();
		try(InputStream inputStream=new FileInputStream(new File("logResponseData.json"))){
			TypeReference<List<RequestStatisticsReq>> typeref=new TypeReference<List<RequestStatisticsReq>>() {};
			if(inputStream.available() > 0) {
				total= mapper.readValue(inputStream, typeref);
			}
		}catch(Exception ex ) {
			System.out.println(ex.getClass() );
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			
		}
		totalreq=total.size();
		totalOkreq=total.parallelStream().filter(p ->  p.getResponsecode() >= 200 && p.getResponsecode() <= 299).count();
		total4xxreq=total.parallelStream().filter(p ->  p.getResponsecode() >= 400 && p.getResponsecode() <= 499).count();
		total500req=total.parallelStream().filter(p ->  p.getResponsecode() >= 500 && p.getResponsecode() <= 599).count();
		sum=total.stream().map(a -> a.getResponsetime()).reduce(0,(a,b) -> a+b);
		
		if(sum >0 && totalreq >0) {
			averagetime=sum/totalreq;
		}else {
			averagetime=0;
		}
		
		Optional<Integer> maxResponseTime=total.stream().map(p -> Integer.valueOf(p.getResponsetime())).max(new Comparator<Integer>() {
	
			@Override
			public int compare(Integer o1, Integer o2) {
				
				return Integer.compare(o1, o2);
			}
		});
		Optional<Integer> minResponseTime=total.stream().map(p -> Integer.valueOf(p.getResponsetime())).max(new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				
				return Integer.compare(o2,o1);
			}
		});
		
		
		RequestStatisticsRes logs=new RequestStatisticsRes(totalreq,totalOkreq,total4xxreq,total500req,averagetime,
				minResponseTime.isPresent() ? minResponseTime.get() : 0,
				maxResponseTime.isPresent() ? maxResponseTime.get() : 0);
		return logs;
	}

}
