package org.oauth.com.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.oauth.com.models.RequestStatisticsReq;
import org.oauth.com.models.RequestStatisticsRes;

public interface Dao {
	public void requestdata(RequestStatisticsReq reqsts) throws FileNotFoundException, IOException;
	public RequestStatisticsRes getStatistics() throws FileNotFoundException, IOException;
	

}
