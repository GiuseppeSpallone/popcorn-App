package com.peppe.popapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {

    int timeout = 2000;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.logo = (ImageView) findViewById(R.id.popcorn);
        logo.setImageResource(R.drawable.popcorn);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent main = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        }, timeout);
    }
}
