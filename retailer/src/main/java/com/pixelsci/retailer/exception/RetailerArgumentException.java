package com.pixelsci.retailer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "parameter not found")
public class RetailerArgumentException extends RuntimeException {

    public RetailerArgumentException(String reason) {
        super(reason);
    }
}
