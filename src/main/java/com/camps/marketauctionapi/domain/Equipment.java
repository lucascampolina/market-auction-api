package com.camps.marketauctionapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"schedule", "defaultMarketRatio", "defaultAuctionRatio", "saleDetails", "classification" })
public class Equipment {
    @JsonIgnore
    String id;
    SalesDetails saleDetails;
    Schedule schedule;
    BigDecimal defaultMarketRatio;
    BigDecimal defaultAuctionRatio;
    Classification classification;

    public Equipment(String id, SalesDetails saleDetails, BigDecimal defaultMarketRatio, BigDecimal defaultAuctionRatio, Classification classification) {
        this.id = id;
        this.saleDetails = saleDetails;
        this.schedule = new Schedule();
        this.defaultMarketRatio = defaultMarketRatio;
        this.defaultAuctionRatio = defaultAuctionRatio;
        this.classification = classification;
    }

    public void addRatios(int year, Ratios ratios) {
        schedule.addRatios(year, ratios);
    }

    public String getId() {
        return id;
    }

    public SalesDetails getSaleDetails() {
        return saleDetails;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public BigDecimal getDefaultMarketRatio() {
        return defaultMarketRatio;
    }

    public BigDecimal getDefaultAuctionRatio() {
        return defaultAuctionRatio;
    }

    public Classification getClassification() {
        return classification;
    }
}