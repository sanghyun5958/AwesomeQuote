package com.knily.awesomequote.helper;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils extends Application{

        private static Utils instance;

        @Override
        public void onCreate() {
            super.onCreate();

            instance = this;

            /*if (BuildConfig.DEBUG)
            {
                Timber.plant(new Timber.DebugTree());
            }

            Timber.i("Creating our Application");
        }*/
        }
        public static Utils getInstance ()
        {
            return instance;
        }

        public static boolean hasNetwork ()
        {
            return instance.checkIfHasNetwork();
        }

        public boolean checkIfHasNetwork()
        {
            ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    /*public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnectedOrConnecting();
    }*/
}