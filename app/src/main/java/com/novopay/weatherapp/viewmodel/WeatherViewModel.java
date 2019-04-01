package com.novopay.weatherapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.util.Log;

import com.novopay.weatherapp.model.Weather;
import com.novopay.weatherapp.utils.ApiService;
import com.novopay.weatherapp.utils.AppConstants;
import com.novopay.weatherapp.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<Weather> forecastList;
    private MutableLiveData<Weather> currentTempList;
    public LiveData<Weather> getForecastDetails() {
        if (forecastList == null) {
            forecastList = new MutableLiveData<Weather>();
            loadForecastData();
        }
        return forecastList;
    }

    public LiveData<Weather> getCurrentTempDetails() {
        if (currentTempList == null) {
            currentTempList = new MutableLiveData<Weather>();
            loadCurrentTempData();
        }
        return currentTempList;
    }
    private void loadForecastData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<Weather> call = api.getForecastData(AppConstants.API_KEY, AppConstants.MY_CITY, AppConstants.NO_OF_DAYS);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Log.d("ResCode>>>",response.code()+"");

                if(response.code()==AppConstants.SUCCESS)
                {
                    forecastList.setValue(response.body());
                }else{
                    AppUtils.getInstance().isError = true;
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t)
            {
                t.printStackTrace();
                AppUtils.getInstance().isError = true;
            }
        });
    }

    private void loadCurrentTempData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<Weather> call = api.getCurrentTemp(AppConstants.API_KEY, AppConstants.MY_CITY);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Log.d("ResCode>>>",response.code()+"");
                if(response.code()==AppConstants.SUCCESS)
                {
                    currentTempList.setValue(response.body());
                }else{
                    AppUtils.getInstance().isError = true;
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                t.printStackTrace();
                AppUtils.getInstance().isError = true;
            }
        });
    }
}
