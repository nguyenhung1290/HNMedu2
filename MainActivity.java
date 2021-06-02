package com.nguyenhung1290.hnmedu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer thgiandenmh2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartAppAd.disableSplash();
        StartAppAd.enableAutoInterstitial();
        StartAppSDK.init(this, "209886933", false);

        thgiandenmh2 = new Timer();
        thgiandenmh2.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                finish();

            }
        },15000);
    }
}