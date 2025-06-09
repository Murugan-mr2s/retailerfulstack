package com.pixelsci.retailer.dto;

import com.pixelsci.retailer.exception.RetailerArgumentException;

public record ShopRecord(String name, String address) {

    public ShopRecord {

        if(name ==null || name.isBlank() )
            throw new RetailerArgumentException("shop name not found");

        if(address==null || address.isBlank()) {
            throw new RetailerArgumentException("shop addess not found");
        }
    }
}
