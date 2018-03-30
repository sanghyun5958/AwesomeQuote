package com.knily.awesomequote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SplashScreen extends AppCompatActivity {
    private static long DELAY = 2000;
    private String selectedLanguage;
    InterstitialAd main_inter;
    AdView main_Banner_AdView;

    Button btn_test;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove title
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },DELAY);


        //create timer
     //   Timer runSplash=new Timer();
        //create timer task

        //   if(!selectedLanguage.equals("")){

        //     }
        //    else{
     //   new Handler().postDelayed(new Runnable() {
   //         @Override
 //           public void run() {
   //             Intent intent = new Intent(SplashScreen.this, Language.class);
   //             startActivity(intent);
   //             finish();
  //   //       }
    //    }, DELAY);
 //   }
        /*TimerTask showSplash=new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashScreen.this,Language.class));
            }
        };
        //start timer
        runSplash.schedule(showSplash,DELAY);*/
}
}
