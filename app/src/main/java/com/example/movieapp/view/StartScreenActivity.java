package com.example.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.movieapp.R;

public class StartScreenActivity extends AppCompatActivity {
    private long delayedTime=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadActivity();

            }
        },delayedTime);
    }
    private void loadActivity(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}