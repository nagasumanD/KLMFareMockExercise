package org.oauth.com.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatisticsReq implements Serializable{

	public static final long serialVersionUID=144234565843453l;
	private String requesttype;
	private int responsecode;
	private int responsetime;
}
