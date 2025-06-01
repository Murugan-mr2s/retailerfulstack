package com.pixelsci.retailer.dto;

import com.pixelsci.retailer.model.Shop;

import java.util.List;

public record RetailerRecord(String name,
                             String address,
                             String email,
                             String phone,
                             List<Shop> shops) {


    public RetailerRecord {

        if(name==null || name.isBlank())
            throw new IllegalArgumentException("ower name not found");

        if(address==null || address.isBlank())
            throw new IllegalArgumentException("ower address not found");

        if(phone==null || phone.isBlank())
            throw new IllegalArgumentException("ower phone not found");
    }
}
