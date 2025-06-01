package com.pixelsci.retailer.dto;

public record ShopRecord(String name, String address) {

    public ShopRecord {

        if(name ==null || name.isBlank() )
            throw new IllegalArgumentException("shop name not found");

        if(address==null || address.isBlank()) {
            throw new IllegalArgumentException("shop addess not found");
        }
    }
}
