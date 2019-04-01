package com.novopay.weatherapp.model;

public class Forecastday {
    private String date;

    private Day day;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public Day getDay ()
    {
        return day;
    }

    public void setDay (Day day)
    {
        this.day = day;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+", day = "+day+"]";
    }
}
