package com.pixelsci.retailer;

import com.pixelsci.retailer.dto.RetailerRecord;
import com.pixelsci.retailer.exception.NoRetailerFoundException;
import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.model.Shop;
import com.pixelsci.retailer.repository.RetailerRepository;
import com.pixelsci.retailer.service.RetailerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RetailerServiceTest {

    @Mock
    RetailerRepository retailerRepository;

    @InjectMocks
    RetailerService retailerService;

    private Retailer retailer,retailer2;


    @BeforeEach
    void setUp() {

        List<Shop> shops = List.of(new Shop("murugan Ideal Store", "Solai Alagupuram"));
        retailer = new Retailer("Murugan", "Madurai-11", "muru@gmail.com", "9159340475", shops);
        retailer.setId(1L);
        retailer.getShops().forEach( shop -> { shop.setId(1L); shop.setRetailer(retailer); }  );

        List<Shop> shops2 = List.of(new Shop("sanjana Ideal Store", "Solai Alagupuram"));
        retailer2 = new Retailer("Sanjana", "Madurai-11", "sanj@gmail.com", "9159340476", shops2);
        retailer2.setId(2L);
        retailer.getShops().forEach( shop -> { shop.setId(2L); shop.setRetailer(retailer2); }  );

    }


    @Test
    @DisplayName("read all retailers")
    void Get_Retailers() {

        Page<Retailer> retailerPage = new PageImpl<>(List.of(retailer, retailer2));
        when(retailerRepository.findAll(any(Pageable.class))).thenReturn(retailerPage);

        List<RetailerRecord> retailers=retailerService.getAllRetailers();
        assertNotNull(retailers);
        assertEquals(2,retailers.size());
        assertTrue(retailers.get(1).name().equalsIgnoreCase("sanjana"));
        assertEquals(1 , retailers.get(1).shops().size());
        verify(retailerRepository, times(1)).findAll(any(Pageable.class));

    }

    @Test
    void GetRetailerById() {
        when( retailerRepository.findById(1L) ). thenReturn(Optional.of(retailer));
        RetailerRecord retailerRecord = retailerService.getRetailer(1L);
        assertNotNull(retailerRecord);
        verify(retailerRepository, times(1)).findById(1L);
    }

    @Test
    void GetRetailerThrowException() {

        when(retailerRepository.findById(99L) ).thenThrow(new NoRetailerFoundException("not found") );
        NoRetailerFoundException exception = assertThrows(NoRetailerFoundException.class, ()-> {
            retailerService.getRetailer(99L);
        });
        verify(retailerRepository,times(1)).findById(99L);
    }

   @Test
   void addNewRetailer() {

        when( retailerRepository.save(any(Retailer.class))).thenReturn(retailer);

        RetailerRecord retailerRecord = new RetailerRecord("Murugan","madurau","muru@gmail.com","9159340375",
                List.of(new Shop("murugan Ideal Store", "Solai Alagupuram")));

        RetailerRecord savedRecord = retailerService.createRetailer(retailerRecord);
        assertNotNull(savedRecord);
        assertNotNull(savedRecord.shops());
        assertEquals(1,savedRecord.shops().size());
        assertTrue( savedRecord.name().equalsIgnoreCase(savedRecord.name()));
        verify(retailerRepository,times(1)).save(any(Retailer.class));
   }


   @Test
   @DisplayName("retailer address update")
   void updateRetailer() {


       RetailerRecord retailerRecord = new RetailerRecord("Murugan","madurai-21","muru@gmail.com","9159340375",
               List.of(new Shop("murugan Ideal Store", "Solai Alagupuram")));

       when(retailerRepository.save(any(Retailer.class))).thenReturn(retailer);
       when(retailerRepository.findByEmailOrPhone(retailerRecord.email(),retailerRecord.phone())).thenReturn(Optional.of(retailer));

       RetailerRecord retailerRecord1=retailerService.updateRetailer(retailerRecord);

       assertNotNull(retailerRecord1);
       assertEquals(retailerRecord.address(), retailerRecord1.address());
       verify(retailerRepository,times(1)).findByEmailOrPhone(any(String.class),any(String.class)) ;
       verify(retailerRepository,times(1)).save( any(Retailer.class) );
   }


}