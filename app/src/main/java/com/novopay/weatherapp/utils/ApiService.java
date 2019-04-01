package com.novopay.weatherapp.utils;

import com.novopay.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "http://api.apixu.com/";

    @GET("v1/current.json")
    Call<Weather> getCurrentTemp(@Query(AppConstants.KEY) String key, @Query(AppConstants.CITY) String city);

    @GET("v1/forecast.json")
    Call<Weather> getForecastData(@Query(AppConstants.KEY) String key, @Query(AppConstants.CITY) String city, @Query(AppConstants.DAYS) String days);
}
