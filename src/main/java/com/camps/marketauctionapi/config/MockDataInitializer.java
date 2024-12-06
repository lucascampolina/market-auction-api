package com.camps.marketauctionapi.config;

import com.camps.marketauctionapi.domain.Category;
import com.camps.marketauctionapi.domain.Classification;
import com.camps.marketauctionapi.domain.Equipment;
import com.camps.marketauctionapi.domain.Ratios;
import com.camps.marketauctionapi.domain.SalesDetails;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class MockDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(MockDataInitializer.class);

    private final Map<String, Equipment> equipmentData = new HashMap<>();

    @PostConstruct
    public void initData() {
        SalesDetails saleDetails1 = new SalesDetails(BigDecimal.valueOf(681252), 122, 17);
        Classification classification1 = new Classification(Category.FURNITURE, "Dozers", "Caterpillar", "D8T");
        Equipment equipment1 = new Equipment("67352", saleDetails1, BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.02), classification1);
        equipment1.addRatios(2006, new Ratios(BigDecimal.valueOf(0.311276), BigDecimal.valueOf(0.181383)));
        equipment1.addRatios(2007, new Ratios(BigDecimal.valueOf(0.317628), BigDecimal.valueOf(0.185085)));
        equipment1.addRatios(2008, new Ratios(BigDecimal.valueOf(0.324111), BigDecimal.valueOf(0.188862)));
        equipment1.addRatios(2009, new Ratios(BigDecimal.valueOf(0.330725), BigDecimal.valueOf(0.192716)));
        equipment1.addRatios(2010, new Ratios(BigDecimal.valueOf(0.363179), BigDecimal.valueOf(0.198498)));
        equipment1.addRatios(2011, new Ratios(BigDecimal.valueOf(0.374074), BigDecimal.valueOf(0.206337)));
        equipment1.addRatios(2012, new Ratios(BigDecimal.valueOf(0.431321), BigDecimal.valueOf(0.213178)));

        SalesDetails saleDetails2 = new SalesDetails(BigDecimal.valueOf(48929), 12, 127);
        Classification classification2 = new Classification(Category.ELECTRONICS, "Boom Lifts", "JLG", "340AJ");
        Equipment equipment2 = new Equipment("87964" ,saleDetails2, BigDecimal.valueOf(0.06), BigDecimal.valueOf(0.06), classification2);
        equipment2.addRatios(2016, new Ratios(BigDecimal.valueOf(0.613292), BigDecimal.valueOf(0.417468)));
        equipment2.addRatios(2017, new Ratios(BigDecimal.valueOf(0.692965), BigDecimal.valueOf(0.473205)));
        equipment2.addRatios(2018, new Ratios(BigDecimal.valueOf(0.980485), BigDecimal.valueOf(0.684991)));
        equipment2.addRatios(2019, new Ratios(BigDecimal.valueOf(1.041526), BigDecimal.valueOf(0.727636)));
        equipment2.addRatios(2020, new Ratios(BigDecimal.valueOf(1.106366), BigDecimal.valueOf(0.772935)));

        equipmentData.put(equipment1.getId(), equipment1);
        equipmentData.put(equipment2.getId(), equipment2);
        logger.info("Mock data initialization complete with {} items", equipmentData.size());
    }

    public Map<String, Equipment> getEquipmentData() {
        logger.info("Fetching equipment data");
        return equipmentData;
    }
}