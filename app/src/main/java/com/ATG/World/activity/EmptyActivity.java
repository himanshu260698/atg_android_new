package com.ATG.World.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ATG.World.R;
import com.ATG.World.preferences.UserPreferenceManager;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        if (UserPreferenceManager.getUserId(EmptyActivity.this).equalsIgnoreCase("")
                || UserPreferenceManager.getUserId(EmptyActivity.this).equals("login")
                || UserPreferenceManager.getUserId(EmptyActivity.this).equals(null)) {
            //  Log.d("Id", "run: social" +UserPreferenceManager.getUserId(SplashActivity.this));
            startActivity(new Intent(EmptyActivity.this, SocialLoginActivity.class));
        } else {
            Log.d("Id", "run: main" + UserPreferenceManager.getUserId(EmptyActivity.this));
            startActivity(new Intent(EmptyActivity.this, MainActivity.class));
        }
        finish();
    }
}
