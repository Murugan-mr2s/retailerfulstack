package com.pixelsci.retailer.repository;

import com.pixelsci.retailer.model.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetailerRepository  extends JpaRepository<Retailer,Long> {

    @Query(value = "SELECT id,owner_name,address, email, phone,shops from retailer where email=:email" ,nativeQuery = true)
    Optional<Retailer>  findByEmail(@Param("email") String email);

    @Query(value = "SELECT id,owner_name,address, email, phone,shops from retailer where phone=:phone" ,nativeQuery = true)
    Optional<Retailer>  findByPhone(@Param("phone") String phone);

    @Query(value = """
                    SELECT id,owner_name,address, email, phone,shops\s
                        from retailer\s
                    where email=:email or phone=:phone
           \s""" ,nativeQuery = true)
    Optional<Retailer>  findByEmailOrPhone(@Param("email") String email, @Param("phone") String phone);

}
