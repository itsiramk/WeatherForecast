package com.novopay.weatherapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.novopay.weatherapp.R;

public class RetryActivity extends AppCompatActivity {

    Button btn_retry;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_retry);
        btn_retry = findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity()
    {
        Intent intent = new Intent(RetryActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
