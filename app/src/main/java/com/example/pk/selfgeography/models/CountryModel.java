package com.example.pk.selfgeography.models;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    public String name;
    public String capital;
    public String region;
    @SerializedName("subregion")
    public String subRegion;
    public long population;
    public double area;

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subRegion='" + subRegion + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }
}
