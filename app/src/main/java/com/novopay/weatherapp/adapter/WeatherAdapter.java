package com.novopay.weatherapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.novopay.weatherapp.R;
import com.novopay.weatherapp.model.Weather;
import com.novopay.weatherapp.utils.AppConstants;
import com.novopay.weatherapp.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.HeroViewHolder> {

    private Context mCtx;
    private Weather weatherList;
    private String str_weather_type,str_temp,str_date;
    public WeatherAdapter(Context mCtx, Weather weatherList) {
        this.mCtx = mCtx;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_weather_item, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {

        str_weather_type = weatherList.getForecast().getForecastday().get(position).getDay().getCondition().getText();
        str_temp = weatherList.getForecast().getForecastday().get(position).getDay().getMintemp_c()
                + AppConstants.CELCIUS + "/"
                +weatherList.getForecast().getForecastday().get(position).getDay().getMaxtemp_c() + AppConstants.CELCIUS;
        str_date = AppUtils.getInstance().formatDate(weatherList.getForecast().getForecastday().get(position).getDate());
        Picasso.with(mCtx)
                .load(AppUtils.getInstance().setWeather_type(str_weather_type))
                .fit()
                .into(holder.imageView);
        holder.txt_temp.setText(str_temp);
        holder.txt_Date.setText(str_date);
        holder.txt_type.setText(str_weather_type);
        if(position == getItemCount() - 1) { holder.mDividerView.setVisibility(View.INVISIBLE);}
    }

    @Override
    public int getItemCount() {
        if (weatherList == null)
            return 0;
        else
            return 7;
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_Date;
        TextView txt_temp;
        TextView txt_type;
        View mDividerView;

        public HeroViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_icon);
            txt_Date = itemView.findViewById(R.id.txt_day);
            txt_temp = itemView.findViewById(R.id.txt_temp);
            txt_type = itemView.findViewById(R.id.txt_type);
            mDividerView = itemView.findViewById(R.id.view);
        }
    }



}