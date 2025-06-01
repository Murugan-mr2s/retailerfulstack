package com.pixelsci.retailer.repository;

import com.pixelsci.retailer.model.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository  extends JpaRepository<Retailer,Long> {
}
