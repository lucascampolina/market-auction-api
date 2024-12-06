package com.camps.marketauctionapi.domain;

import java.math.BigDecimal;

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
}
