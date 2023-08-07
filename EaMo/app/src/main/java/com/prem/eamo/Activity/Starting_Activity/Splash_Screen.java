package com.prem.eamo.Activity.Starting_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.prem.eamo.Activity.Home.Home_Activity;
import com.prem.eamo.R;
import com.prem.eamo.Utilities.SessionManager;

public class Splash_Screen extends AppCompatActivity {

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.secondary_color));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionManager sessionManager = new SessionManager(Splash_Screen.this);
                if (sessionManager.isLogin()) {
                    startActivity(new Intent(Splash_Screen.this, Home_Activity.class));
                }else {
                    startActivity(new Intent(Splash_Screen.this, Login.class));
                }
                finish();
            }
        },3000);
    }
}