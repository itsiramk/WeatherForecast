package com.novopay.weatherapp.model;

public class Day {

    private String avgtemp_c;

    private String maxtemp_c;

    private String mintemp_c;

    private WeatherCondition condition;

    public String getAvgtemp_c() {
        return avgtemp_c;
    }

    public void setAvgtemp_c(String avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(String maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(String mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Day{" +
                "avgtemp_c='" + avgtemp_c + '\'' +
                ", maxtemp_c='" + maxtemp_c + '\'' +
                ", mintemp_c='" + mintemp_c + '\'' +
                ", condition=" + condition +
                '}';
    }
}
