package org.oauth.com.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestStatisticsRes {
	private long totalRequest;
	private long requestOk;
	private long request4xx;
	private long request5xx;
	private long averageRespTime;
	private int minResptime;
	private int maxResptime;

}
