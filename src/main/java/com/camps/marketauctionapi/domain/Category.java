package com.camps.marketauctionapi.domain;

import java.util.Arrays;

public enum Category {
    ELECTRONICS,
    FURNITURE,
    VEHICLES,
    TOOLS,
    OTHER;

    public static boolean isValidCategory(String category) {
        return Arrays.stream(Category.values())
                .anyMatch(c -> c.name().equalsIgnoreCase(category));
    }
}
