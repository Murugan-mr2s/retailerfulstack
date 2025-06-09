package com.pixelsci.retailer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "retailer not found")
public class NoRetailerFoundException extends RuntimeException {

    public NoRetailerFoundException(String reason) {
        super(reason);
    }
}
