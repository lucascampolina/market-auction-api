package com.camps.marketauctionapi.service;

import com.camps.marketauctionapi.config.MockDataInitializer;
import com.camps.marketauctionapi.domain.Equipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    private Map<String, Equipment> equipmentData = new HashMap<>();
    private final MockDataInitializer mockDataInitializer;

    public EquipmentService(MockDataInitializer mockDataInitializer) {
        this.mockDataInitializer = mockDataInitializer;
    }

    @PostConstruct
    public void initData() {
        equipmentData = mockDataInitializer.getEquipmentData();
        logger.info("Equipment data initialized with {} items", equipmentData.size());
    }

    public Map<String, Equipment> getAllEquipment() {
        return equipmentData;
    }

    public Map<String, Double> calculatesValues(String modelId, int year) {
        Equipment equipment = Optional.ofNullable(equipmentData.get(modelId))
                .orElseThrow(() -> new IllegalArgumentException("Equipment ID " + modelId + " not found."));
        return equipment.calculateValues(year);
    }
}