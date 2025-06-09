package com.pixelsci.retailer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "shop not found")
public class NoShopFoundException extends RuntimeException {

    public NoShopFoundException(String reason) {
        super(reason);
    }
}
