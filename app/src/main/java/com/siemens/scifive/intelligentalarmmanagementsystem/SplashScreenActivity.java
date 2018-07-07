package com.siemens.scifive.intelligentalarmmanagementsystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.siemens.scifive.intelligentalarmmanagementsystem.activities.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //BECAUSE OF HANDLER POST DELAYED, THIS CODE WILL EXECUTE AFTER SPECIFIED delayMillis
                        Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(i);
                        SplashScreenActivity.this.finish();

                    }
                }, 3000
        );

    }
}
