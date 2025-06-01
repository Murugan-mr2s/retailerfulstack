package com.pixelsci.retailer.dto;

import com.pixelsci.retailer.model.Retailer;

public interface RetailerMapper {

    public static Retailer toRetailer(RetailerRecord record) {

       return new Retailer(record.name() ,
                record.address(),
                record.email(),
                record.phone(),
                record.shops() );
    }

    public static  RetailerRecord toRetailerRecord(Retailer retailer) {

        return new RetailerRecord( retailer.getOwerName(),
                retailer.getAddress(),
                retailer.getEmail(),
                retailer.getPhone(),
                retailer.getShops());
    }
}
