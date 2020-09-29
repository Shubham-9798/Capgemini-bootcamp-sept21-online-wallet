package com.onlinewallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * Entity notfound experience
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 * @param msg
	 */
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
