package com.novopay.weatherapp.model;

import java.util.ArrayList;

public class Forecast {
    private ArrayList<Forecastday> forecastday;

    public ArrayList<Forecastday> getForecastday ()
    {
        return forecastday;
    }

    public void setForecastday (ArrayList<Forecastday> forecastday)
    {
        this.forecastday = forecastday;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [forecastday = "+forecastday+"]";
    }
}
