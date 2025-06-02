package com.pixelsci.retailer.repository;

import com.pixelsci.retailer.model.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetailerRepository  extends JpaRepository<Retailer,Long> {

    @Query(value = "SELECT * from retailers where email=:email" ,nativeQuery = false)
    Optional<Retailer>  findByEmail(@Param("email") String email);

    @Query(value = "SELECT * from retailers where phone=:phone" ,nativeQuery = false)
    Optional<Retailer>  findByPhone(@Param("phone") String phone);

    @Query(value = "SELECT * from retailers where email=:email or phone=:phone" ,nativeQuery = false)
    Optional<Retailer>  findByEmailOrPhone(@Param("email") String email, @Param("phone") String phone);

}
