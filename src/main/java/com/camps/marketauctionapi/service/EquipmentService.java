package com.camps.marketauctionapi.service;

import com.camps.marketauctionapi.config.MockDataInitializer;
import com.camps.marketauctionapi.domain.Equipment;
import com.camps.marketauctionapi.domain.Ratios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Invalid year: " + year + ". Year must be between 1900 and the current year.");
        }

        Equipment equipment = equipmentData.get(modelId);
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment ID " + modelId + " not found.");
        }

        Ratios ratios = equipment.getSchedule().getYearRatios().get(year);
        return getValuesResponse(ratios, equipment);
    }

    private static Map<String, Double> getValuesResponse(Ratios ratios, Equipment equipment) {
        BigDecimal marketRatio = (ratios != null) ? ratios.getMarketRatio() : equipment.getDefaultMarketRatio();
        BigDecimal auctionRatio = (ratios != null) ? ratios.getAuctionRatio() : equipment.getDefaultAuctionRatio();

        double marketValue = equipment.getSaleDetails().getCost().multiply(marketRatio)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
        double auctionValue = equipment.getSaleDetails().getCost().multiply(auctionRatio)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();

        Map<String, Double> response = new HashMap<>();
        response.put("marketValue", marketValue);
        response.put("auctionValue", auctionValue);
        return response;
    }
}