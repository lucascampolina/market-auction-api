package com.camps.marketauctionapi.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Ratios {

    BigDecimal marketRatio;
    BigDecimal auctionRatio;

    public Ratios(BigDecimal marketRatio, BigDecimal auctionRatio) {
        this.marketRatio = marketRatio;
        this.auctionRatio = auctionRatio;
    }

    public BigDecimal getMarketRatio() {
        return marketRatio;
    }

    public BigDecimal getAuctionRatio() {
        return auctionRatio;
    }

    public Map<String, Double> calculateValues(BigDecimal cost) {
        double marketValue = cost.multiply(marketRatio).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double auctionValue = cost.multiply(auctionRatio).setScale(2, RoundingMode.HALF_UP).doubleValue();

        Map<String, Double> values = new HashMap<>();
        values.put("marketValue", marketValue);
        values.put("auctionValue", auctionValue);
        return values;
    }
}
