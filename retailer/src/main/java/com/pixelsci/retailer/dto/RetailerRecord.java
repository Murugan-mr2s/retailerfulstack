package com.pixelsci.retailer.dto;

import com.pixelsci.retailer.exception.RetailerArgumentException;
import com.pixelsci.retailer.model.Shop;

import java.util.List;

public record RetailerRecord(String name,
                             String address,
                             String email,
                             String phone,
                             List<Shop> shops) {


    public RetailerRecord(String name,
                          String address,
                          String email,
                          String phone,
                          List<Shop> shops) {

        if(name==null || name.isBlank())
            throw new RetailerArgumentException("owner name not found");

        if(address==null || address.isBlank())
            throw new RetailerArgumentException("owner address not found");

        if(phone==null || phone.isBlank())
            throw new RetailerArgumentException("owner phone not found");

        this.name= name;
        this.address= address;
        this.email = email;
        this.phone = phone;
        this.shops = List.copyOf(shops);

    }
}
