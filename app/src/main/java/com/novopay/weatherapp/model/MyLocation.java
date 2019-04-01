package com.novopay.weatherapp.model;

public class MyLocation {

    private String country;

    private String name;

    private String lon;

    private String region;

    private String lat;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "MyLocation{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", lon='" + lon + '\'' +
                ", region='" + region + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
