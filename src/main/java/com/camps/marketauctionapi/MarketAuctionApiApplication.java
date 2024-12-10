package com.camps.marketauctionapi;

import com.camps.marketauctionapi.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootApplication
public class MarketAuctionApiApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MarketAuctionApiApplication.class);
    private final ApplicationContext applicationContext;

    public MarketAuctionApiApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(MarketAuctionApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EquipmentService equipmentService = applicationContext.getBean(EquipmentService.class);

        try {
            Map<String, Double> result1 = equipmentService.calculatesValues("67352", 2007);
            logger.info("Result for Year 2007 ID 67352: {}", result1);
        } catch (Exception e) {
            logger.error("Error calculating values for Year 2007 ID 67352", e);
        }
    }
}
