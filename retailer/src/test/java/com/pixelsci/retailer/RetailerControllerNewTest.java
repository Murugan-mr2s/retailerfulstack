package com.pixelsci.retailer;

import com.pixelsci.retailer.dto.RetailerRecord;
import com.pixelsci.retailer.model.Shop;
import com.pixelsci.retailer.service.RetailerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RetailerControllerNewTest {

    @Autowired
    private MockMvcTester mvcTester;

    @MockitoBean
    RetailerService retailerService;

    private RetailerRecord retailer;
    private  RetailerRecord retailer2;


    @BeforeEach
    void setUp() {

        List<Shop> shops = List.of(new Shop("murugan Ideal Store", "Solai Alagupuram"));
        retailer = new RetailerRecord("Murugan", "Madurai-11", "muru@gmail.com", "0151341111", shops);

        List<Shop> shops2 = List.of(new Shop("sanjana Ideal Store", "Solai Alagupuram"));
        retailer2 = new RetailerRecord("Sanjana", "Madurai-11", "sanj@gmail.com", "0151341112", shops2);

    }


    @Test
    @DisplayName("read all retailers from db")
    void GetAllRetailer() {

        when(retailerService.getAllRetailers()).thenReturn(List.of(retailer,retailer2));

        mvcTester.get().uri("/api/v1/retailers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.OK);

        verify(retailerService,times(1)).getAllRetailers();
    }

    @Test
    @DisplayName("read retailer by id")
    void GetRetailerById() {
        Long id = 1L;
        when(retailerService.getRetailer(id)).thenReturn(retailer);

        mvcTester.get()
                .uri("/api/v1/retailers/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .convertTo(RetailerRecord.class)
                .matches( m-> m.name().equalsIgnoreCase("murugan") );


        verify(retailerService,times(1)).getRetailer(1L);
    }

}
