package com.camps.marketauctionapi.controller;

import com.camps.marketauctionapi.domain.Category;
import com.camps.marketauctionapi.domain.Classification;
import com.camps.marketauctionapi.domain.Equipment;
import com.camps.marketauctionapi.domain.SalesDetails;
import com.camps.marketauctionapi.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MarketControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllEquipment_returnsEquipmentList() throws Exception {
        SalesDetails salesDetails = new SalesDetails(new BigDecimal("1000"), 122, 17);
        Classification classification = new Classification(Category.FURNITURE, "Dozers", "Caterpillar", "D8T");
        Equipment equipment = new Equipment(salesDetails, new BigDecimal("0.1"), new BigDecimal("0.2"), classification);
        Map<String, Equipment> equipmentData = Collections.singletonMap("1", equipment);

        when(equipmentService.getAllEquipment()).thenReturn(equipmentData);

        mockMvc.perform(get("/api/equipment"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"1\":{}}"));
    }

    @Test
    void calculatesValues_validModelIdAndYear_returnsValues() throws Exception {
        Map<String, Double> values = Map.of("marketValue", 300.00, "auctionValue", 400.00);

        when(equipmentService.calculatesValues(1L, 2020)).thenReturn(values);

        mockMvc.perform(get("/api/equipment/1/2020"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"marketValue\":300.00,\"auctionValue\":400.00}"));
    }
}