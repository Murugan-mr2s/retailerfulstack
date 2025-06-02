package com.pixelsci.retailer.controller;

import com.pixelsci.retailer.dto.RetailerRecord;
import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.service.RetailerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/retailers")
public record RetailerController(  RetailerService retailerService ) {


    @GetMapping
    public ResponseEntity<List<RetailerRecord>> allRetailers() {
         List<RetailerRecord> retailers= retailerService.getAllRetailers();
         return  ResponseEntity.ofNullable(retailers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetailerRecord> getRetailer(@PathVariable Long id) {
        RetailerRecord  retailer = retailerService.getRetailer(id);
        return ResponseEntity.ofNullable(retailer);
    }

    @PostMapping
    public  ResponseEntity<RetailerRecord> addRetailer(RetailerRecord retailer) {
        RetailerRecord  retailerRecord = retailerService.createRetailer(retailer);
        return ResponseEntity.ofNullable(retailerRecord);
    }

    @PutMapping
    public ResponseEntity<RetailerRecord> updateRetailer(RetailerRecord retailer) {
        RetailerRecord  retailerRecord=retailerService.updateRetailer(retailer);
        return ResponseEntity.ofNullable(retailerRecord);
    }

    @DeleteMapping
    public void deleteMapping(RetailerRecord retailer) {
        retailerService.deleteRetailer(retailer);
    }

}
