package com.suraj.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BasketNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BasketNotFoundException(String message) {
        super(message);
    }
}