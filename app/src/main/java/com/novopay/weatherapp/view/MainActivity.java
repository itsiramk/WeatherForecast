package com.novopay.weatherapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.weatherapp.R;
import com.novopay.weatherapp.adapter.WeatherAdapter;
import com.novopay.weatherapp.model.Weather;
import com.novopay.weatherapp.utils.AppConstants;
import com.novopay.weatherapp.utils.AppUtils;
import com.novopay.weatherapp.viewmodel.WeatherViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private ProgressBar pBar;
    private Context context;
    private TextView txt_current_tmp,txt_city;
    private RelativeLayout rel_loading;
    private android.support.design.widget.AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        if(!AppUtils.getInstance().isInternetConnected(context))
        {
            finish();
            Toast.makeText(context,AppConstants.CHECK_CONNECTION,Toast.LENGTH_LONG).show();
        }
        initViews();
    }

    private void initViews()
    {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        recyclerView = findViewById(R.id.rc_view);
        appBarLayout = findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(AppConstants.APP_NAME);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });        rel_loading = findViewById(R.id.rel_loading);
        txt_city = findViewById(R.id.txt_city);
        txt_current_tmp = findViewById(R.id.txt_current_temp);
        new LoadTempTask().execute();
        Log.d("Erorrr>>>>",""+AppUtils.getInstance().isError);

    }
    private void showForecastData(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
         WeatherViewModel model = ViewModelProviders.of(this).get(WeatherViewModel.class);

        model.getForecastDetails().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(@Nullable Weather weather) {

                if(!AppUtils.getInstance().isError)
                {
                    weatherAdapter = new WeatherAdapter(context, weather);
                    recyclerView.setAdapter(weatherAdapter);
                }else
                {
                    openErrorActivity();
                }
            }
        });
    }

    private void showCurrentTempData(){
        WeatherViewModel model = ViewModelProviders.of(this).get(WeatherViewModel.class);
        model.getCurrentTempDetails().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(@Nullable Weather weather) {

                if(!AppUtils.getInstance().isError)
                {
                    txt_city.setText(weather.getLocation().getName());
                    txt_current_tmp.setText(weather.getCurrent().getTemp_c() + AppConstants.CELCIUS);
                }else
                {
                    openErrorActivity();
                }

            }
        });
    }

    private void openErrorActivity() {
        Intent intent = new Intent(context,RetryActivity.class);
        startActivity(intent);
    }


    public class LoadTempTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(!AppUtils.getInstance().isError){
                rel_loading.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                appBarLayout.setVisibility(View.VISIBLE);
            }

        }
        @Override
        protected Void doInBackground(Void... voids) {

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    showCurrentTempData();
                    showForecastData();
                }
            });

           return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pBar = findViewById(R.id.progressBar);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
