package com.novopay.weatherapp.model;

import android.location.Location;

public class Weather {
    private Current current;

    private MyLocation location;

    private Forecast forecast;

    public Current getCurrent ()
    {
        return current;
    }

    public void setCurrent (Current current)
    {
        this.current = current;
    }

    public MyLocation getLocation ()
    {
        return location;
    }

    public void setLocation (MyLocation location)
    {
        this.location = location;
    }

    public Forecast getForecast ()
    {
        return forecast;
    }

    public void setForecast (Forecast forecast)
    {
        this.forecast = forecast;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [current = "+current+", location = "+location+", forecast = "+forecast+"]";
    }
}
