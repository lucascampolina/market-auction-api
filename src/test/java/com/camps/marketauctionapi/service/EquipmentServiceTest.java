package com.camps.marketauctionapi.service;

import com.camps.marketauctionapi.config.MockDataInitializer;
import com.camps.marketauctionapi.domain.Category;
import com.camps.marketauctionapi.domain.Classification;
import com.camps.marketauctionapi.domain.Equipment;
import com.camps.marketauctionapi.domain.Ratios;
import com.camps.marketauctionapi.domain.SalesDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class EquipmentServiceTest {

    private EquipmentService equipmentService;
    private MockDataInitializer mockDataInitializer;

    @BeforeEach
    public void setUp() {
        mockDataInitializer = Mockito.mock(MockDataInitializer.class);
        equipmentService = new EquipmentService(mockDataInitializer);
    }

    @Test
    void calculatesValues_validModelIdAndYear_returnsCorrectValues() {
        Equipment equipment = new Equipment(new SalesDetails(new BigDecimal("1000"), 122, 17), new BigDecimal("0.1"), new BigDecimal("0.2"), new Classification(Category.FURNITURE, "Dozers", "Caterpillar", "D8T"));
        Ratios ratios = new Ratios(new BigDecimal("0.3"), new BigDecimal("0.4"));
        equipment.addRatios(2020, ratios);

        Map<Long, Equipment> equipmentData = new HashMap<>();
        equipmentData.put(1L, equipment);

        when(mockDataInitializer.getEquipmentData()).thenReturn((Map) equipmentData);
        equipmentService.initData();

        Map<String, Double> values = equipmentService.calculatesValues(1L, 2020);

        assertEquals(300.00, values.get("marketValue"));
        assertEquals(400.00, values.get("auctionValue"));
    }

    @Test
    void calculatesValues_invalidYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> equipmentService.calculatesValues(1L, 1800));
    }

    @Test
    void calculatesValues_nonExistentModelId_throwsIllegalArgumentException() {
        when(mockDataInitializer.getEquipmentData()).thenReturn(new HashMap<>());
        equipmentService.initData();

        assertThrows(IllegalArgumentException.class, () -> equipmentService.calculatesValues(1L, 2020));
    }

    @Test
    void getAllEquipment_returnsAllEquipment() {
        Map<String, Equipment> equipmentData = new HashMap<>();
        equipmentData.put("1", new Equipment(new SalesDetails(new BigDecimal("1000"), 122, 17), new BigDecimal("0.1"), new BigDecimal("0.2"), new Classification(Category.FURNITURE, "Dozers", "Caterpillar", "D8T")));

        when(mockDataInitializer.getEquipmentData()).thenReturn(equipmentData);
        equipmentService.initData();

        assertEquals(equipmentData, equipmentService.getAllEquipment());
    }
}