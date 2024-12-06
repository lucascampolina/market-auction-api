package com.camps.marketauctionapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    @JsonProperty("years")
    private Map<Integer, Ratios> yearRatios;

    public Schedule() {
        this.yearRatios = new HashMap<>();
    }

    public void addRatios(int year, Ratios ratios) {
        this.yearRatios.put(year, ratios);
    }

    public Map<Integer, Ratios> getYearRatios() {
        return yearRatios;
    }
}