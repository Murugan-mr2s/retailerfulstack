package com.pixelsci.retailer;


import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.model.Shop;
import com.pixelsci.retailer.repository.RetailerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RetailerJpaDataTest {

    @Autowired
    RetailerRepository retailerRepository;


    @BeforeEach
    void setUp() {
        retailerRepository.deleteAll();
    }

    static Stream<Retailer> retailerProvider() {
        var shops=List.of(  new Shop("muru idly shop", "solai alagu puram")  );
        Retailer retailer = new Retailer("murugan", "madurai-21",
                "murugan@gmaill.com","9159340375", shops);
        shops.forEach( shop -> shop.setRetailer(retailer) );
        return Stream.of(retailer);
    }


    @ParameterizedTest
    @MethodSource("retailerProvider")
    void retailerSaveTest(Retailer retailer) {

        Retailer savedRetailer = retailerRepository.save(retailer);
        assertNotNull(savedRetailer.getId());

        assertThat( retailerRepository.findAll() )
                .hasSize(1)
                .first()
                .extracting( Retailer::getOwnerName)
                .isEqualTo("murugan");

    }



    @Test
    void updateRetailer() {

        List<Shop> shops=new ArrayList<>();
        shops.add(new Shop("muru idly shop", "solai alagu puram"));

        Retailer retailer = new Retailer("murugan", "madurai-21",
                "murugan@gmaill.com","9159340375", shops);
        shops.forEach( shop -> shop.setRetailer(retailer) );
         retailerRepository.save(retailer);

        retailer.setAddress("solai alagupuram 4th street");
        shops.add(new Shop("muru idly shop222", "solai alagu puram"));
        shops.forEach( shop -> shop.setRetailer(retailer) );
        Retailer updatedRetailer = retailerRepository.save(retailer);


        assertNotNull(updatedRetailer.getId());
        assertEquals(2, updatedRetailer.getShops().size());
        assertEquals("solai alagupuram 4th street" , updatedRetailer.getAddress());

    }




    @ParameterizedTest
    @MethodSource("retailerProvider")
    void deleteRetailer(Retailer retailer) {

        Retailer savedRetailer = retailerRepository.save(retailer);

        assertNotNull(savedRetailer.getId());
        retailerRepository.deleteById(savedRetailer.getId());

        Optional<Retailer> optionalRetailer= retailerRepository.findById(savedRetailer.getId());
        assertTrue(optionalRetailer.isEmpty());

    }

}
