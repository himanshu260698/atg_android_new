package com.ATG.World.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ATG.World.R;
import com.ATG.World.preferences.UserPreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(UserPreferenceManager.getUserId(SplashActivity.this).equalsIgnoreCase("")){
            startActivity(new Intent(SplashActivity.this, SocialLoginActivity.class));
            finish();

        }
        else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }



        /*if (SessionClass.getUserId(SplashScreenActivity.this).equalsIgnoreCase("")) {
            it.putExtra("key", "1");
            it.setClass(SplashScreenActivity.this, GetStartedActivity.class);
        } else {
            Bundle bundle = new Bundle();

            it.setClass(SplashScreenActivity.this, MainActivity.class);
            bundle.putString("flg", "1");
            it.putExtras(bundle);

        }
        startActivity(it);
        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_left_out);
        finish();
        */


    }
}
