package com.ATG.World.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       //We will use shared preference to check login activity which we will add later
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();


    }
}
