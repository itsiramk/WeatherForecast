package com.novopay.weatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.novopay.weatherapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    private static AppUtils mInstance;
    public boolean isError = false;

    public AppUtils() {

        mInstance = this;
    }

    public static AppUtils getInstance() {


        if (mInstance != null) {

            return mInstance;
        } else {

            new AppUtils();
            return mInstance;
        }
    }

    public int setWeather_type(String weather_type)
    {
        int img_path;
        if(weather_type.equalsIgnoreCase(AppConstants.CLEAR))
        {
            img_path = R.drawable.clear;
        }else if(weather_type.equalsIgnoreCase(AppConstants.SUN))
        {
            img_path = R.drawable.sun;

        }else if(weather_type.equalsIgnoreCase(AppConstants.STORM))
        {
            img_path = R.drawable.storm;

        }else if(weather_type.equalsIgnoreCase(AppConstants.RAINY) || weather_type.equalsIgnoreCase(AppConstants.MIST))
        {
            img_path = R.drawable.rain;

        }else if(weather_type.equalsIgnoreCase(AppConstants.CLOUDY) || weather_type.equalsIgnoreCase(AppConstants.PARTLY_CLOUDY))
        {
            img_path = R.drawable.clouds;
        }else
        {
            img_path = R.drawable.sun;
        }
        return img_path;
    }

    public String formatDate(String str_date) {
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.applyPattern("dd MMM yyyy");
        String newDate = df.format(date);
        Log.d("NewDate>>>>",newDate);
        if(fetchTomDate(fetchDate()).equalsIgnoreCase(newDate))
        {
            return AppConstants.TOMORROW;
        }
        else if(fetchDate().equalsIgnoreCase(newDate))
        {
            return AppConstants.TODAY;
        }

        else return newDate;
    }
    public String fetchDate()
    {
        String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        return date;
    }
    public String fetchTomDate(String dt)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);
        String tomorrowAsString = sdf.format(c.getTime());


        return tomorrowAsString;
    }

    public boolean isInternetConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
