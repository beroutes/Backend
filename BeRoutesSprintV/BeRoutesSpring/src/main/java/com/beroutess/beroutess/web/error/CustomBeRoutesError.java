package com.beroutess.beroutess.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomBeRoutesError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CustomBeRoutesError(String message) {
		super(message);
		
	}
	

		
	
}
