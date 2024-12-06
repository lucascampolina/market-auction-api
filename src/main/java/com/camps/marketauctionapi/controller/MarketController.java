package com.camps.marketauctionapi.controller;

import com.camps.marketauctionapi.domain.Equipment;
import com.camps.marketauctionapi.service.EquipmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/equipment")
public class MarketController {

    private final EquipmentService equipmentService;

    public MarketController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public Map<String, Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/{modelId}/{year}")
    public Map<String, Double> calculatesValues(@PathVariable long modelId, @PathVariable int year) {
        return equipmentService.calculatesValues(modelId, year);
    }
}