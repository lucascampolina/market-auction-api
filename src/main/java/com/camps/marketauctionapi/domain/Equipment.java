package com.camps.marketauctionapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

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

    public Map<String, Double> calculateValues(int year) {
        validateYear(year);

        Ratios ratios = schedule.getYearRatios().get(year);
        if (ratios == null) {
            ratios = new Ratios(defaultMarketRatio, defaultAuctionRatio);
        }

        return ratios.calculateValues(saleDetails.getCost());
    }

    private void validateYear(int year) {
        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Invalid year: " + year + ". Year must be between 1900 and " + Calendar.getInstance().get(Calendar.YEAR) + ".");
        }
    }
}