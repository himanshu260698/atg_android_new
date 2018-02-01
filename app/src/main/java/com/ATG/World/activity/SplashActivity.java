package com.ATG.World.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.ATG.World.R;

public class SplashActivity extends AppCompatActivity {

    //    private ImageView splash_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        splash_layout=findViewById(R.id.splash_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Intent intent = new Intent(this, SocialLoginActivity.class);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);
                        startActivity(intent);
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



//        if(UserPreferenceManager.getUserId(SplashActivity.this).equalsIgnoreCase("")){
//            startActivity(new Intent(SplashActivity.this, SocialLoginActivity.class));
//            finish();
//
//        }
//        else {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
//        }




