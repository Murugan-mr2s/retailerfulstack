package com.pixelsci.retailer.controller;

import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.service.RetailerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/retailers")
public record RetailerController(  RetailerService retailerService ) {


    @GetMapping
    public ResponseEntity<List<Retailer>> allRetailers() {
         List<Retailer> retailers= retailerService.getAllRetailers();
         return  ResponseEntity.ofNullable(retailers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retailer> getRetailer(@PathVariable Long id) {
        Retailer  retailer = retailerService.getRetailers(id);
        return ResponseEntity.ofNullable(retailer);
    }

    @PostMapping
    public  void addRetailer(Retailer retailer) {
         retailerService.createRetailer(retailer);
    }

    @PutMapping
    public void updateRetailer(Retailer retailer) {
        retailerService.updateRetailer(retailer);
    }

    @DeleteMapping
    public void deleteMapping(Retailer retailer) {
        retailerService.deleteRetailer(retailer);
    }

}
