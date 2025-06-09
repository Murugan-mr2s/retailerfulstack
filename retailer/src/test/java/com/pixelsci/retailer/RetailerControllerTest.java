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

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Retailer Controller Test")
public class RetailerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RetailerService retailerService;

    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("read all retailers")
    void GetAllRetailers() throws Exception {

        List<RetailerRecord> records = List.of(retailer,retailer2);
        when(retailerService.getAllRetailers()).thenReturn(records );

        mockMvc.perform( get("/api/v1/retailers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk() )
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Murugan") )
                .andExpect(jsonPath("$[1].name").value("Sanjana"));

        verify(retailerService,times(1)).getAllRetailers();
    }

    @Test
    @DisplayName("get retailer by id")
    void GetRetailerById() throws Exception {

        when(retailerService.getRetailer(1L)).thenReturn(retailer);

        mockMvc.perform( get("/api/v1/retailers/{id}",1)
                .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Murugan"))
                .andExpect(jsonPath("$.shops").isArray());

        verify(retailerService,times(1)).getRetailer(any(Long.class));
    }


    @Test
    @DisplayName("add new retailer")
    void addNewRetailer() throws Exception {

        when(retailerService.createRetailer(any(RetailerRecord.class))).thenReturn(retailer);

        mockMvc.perform( post("/api/v1/retailers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(retailer))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Murugan"));

    }

}
