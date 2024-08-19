package com.example.foodplanner.Model;

public class Country{
    private String countryName;
    private int imageResourceId;

    public Country(String countryName, int imageResourceId) {
        this.countryName = countryName;
        this.imageResourceId = imageResourceId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
