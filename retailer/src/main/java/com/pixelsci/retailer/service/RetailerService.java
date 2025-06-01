package com.pixelsci.retailer.service;

import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.repository.RetailerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RetailerService(RetailerRepository retailerRepository) {
    public List<Retailer> getAllRetailers() {
    }

    public Retailer getRetailers(Long id) {
    }

    public void createRetailer(Retailer retailer) {
    }

    public void updateRetailer(Retailer retailer) {
    }

    public void deleteRetailer(Retailer retailer) {
    }
}
