package com.ATG.World.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ATG.World.R;
import com.ATG.World.preferences.UserPreferenceManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(SplashActivity.this, EmptyActivity.class));



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(2000);
                     /*   if (UserPreferenceManager.getUserId(SplashActivity.this).equalsIgnoreCase("")
                                ||UserPreferenceManager.getUserId(SplashActivity.this).equals("login")
                                ||UserPreferenceManager.getUserId(SplashActivity.this).equals(null)) {
                            //  Log.d("Id", "run: social" +UserPreferenceManager.getUserId(SplashActivity.this));
                            startActivity(new Intent(SplashActivity.this, SocialLoginActivity.class));
                        } else {
                            Log.d("Id", "run: main" +UserPreferenceManager.getUserId(SplashActivity.this));
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                        */
                        finish();

                    }
                } catch (InterruptedException ex) {
                }

                // TODO
            }
        };

        thread.start();
    }
}




