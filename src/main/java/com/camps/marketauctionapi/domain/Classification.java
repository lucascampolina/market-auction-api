package com.camps.marketauctionapi.domain;

public class Classification {
    String category;
    String subcategory;
    String make;
    String model;

    public Classification(String category, String subcategory, String make, String model) {
        this.category = category;
        this.subcategory = subcategory;
        this.make = make;
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}