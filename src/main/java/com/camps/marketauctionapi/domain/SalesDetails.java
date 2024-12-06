package com.camps.marketauctionapi.domain;

import java.math.BigDecimal;

public class SalesDetails {

    BigDecimal cost;
    int retailSaleCount;
    int auctionSaleCount;

    public SalesDetails(BigDecimal cost, int retailSaleCount, int auctionSaleCount) {
        this.cost = cost;
        this.retailSaleCount = retailSaleCount;
        this.auctionSaleCount = auctionSaleCount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getRetailSaleCount() {
        return retailSaleCount;
    }

    public int getAuctionSaleCount() {
        return auctionSaleCount;
    }
}
