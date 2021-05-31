package org.oauth.com.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.oauth.com.models.RequestStatisticsReq;
import org.oauth.com.models.RequestStatisticsRes;

public interface ServicesLayer {

	String gerAriportsData();

	String Fares(String orgin, String distination, String currency);

	void requestdata(RequestStatisticsReq reqsts) throws FileNotFoundException, IOException;

	RequestStatisticsRes getStatistics() throws FileNotFoundException, IOException;
	

}
