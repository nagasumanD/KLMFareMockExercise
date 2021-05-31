package org.oauth.com.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> globalexceptionHandler(){
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("Some thing went wrong please try later","internal server error") , HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
