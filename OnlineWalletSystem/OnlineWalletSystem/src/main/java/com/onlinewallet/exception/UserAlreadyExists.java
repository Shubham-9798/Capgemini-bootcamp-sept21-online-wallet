package com.onlinewallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * EntityAlreadyExists
 *
 */

@ResponseStatus(HttpStatus.ALREADY_REPORTED) 
public class UserAlreadyExists extends RuntimeException {
	/**
	 * 
	 * @param msg
	 */

	public UserAlreadyExists(String msg) {
		super(msg);
	}

}
