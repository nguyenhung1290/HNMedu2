package com.nguyenhung1290.hnmedu2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

public class MainActivity4 extends AppCompatActivity {

    Button gotomh5, gotomh6;

    private InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            themQangCao();
            }
        });


        StartAppAd.disableSplash();
        StartAppAd.disableAutoInterstitial();
        StartAppSDK.init(this, "209886933", false);

        gotomh5=(Button)findViewById(R.id.btndata);
        gotomh6=(Button)findViewById(R.id.btnexam);
        gotomh5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity4.this);
                } else {
                    Log.d("---Admob", "The interstitial ad wasn't ready yet.");
                    Intent act5= new Intent(MainActivity4.this,MainActivity5.class);
                    startActivity(act5);
                }

            }
        });
        gotomh6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity4.this);
                } else {
                    Log.d("---Admob", "The interstitial ad wasn't ready yet.");
                    Intent act6= new Intent(MainActivity4.this,MainActivity6.class);
                    startActivity(act6);
                }

            }
        });

    }

    private void themQangCao() {
        AdRequest adRequest = new AdRequest.Builder().build();
        themQuangCaoFull(adRequest);

    }

    private void themQuangCaoFull(AdRequest adRequest){
        InterstitialAd.load(this,"ca-app-pub-7601848265461189/6388519969", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.d("---Admob", "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("Admob", "The ad was dismissed.");
                        Intent act6= new Intent(MainActivity4.this,MainActivity6.class);
                        startActivity(act6);
                        Intent act5= new Intent(MainActivity4.this,MainActivity5.class);
                        startActivity(act5);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("Admob", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("Admob", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("---Admob", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }

}
