package com.example.campuswayfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 1500;  //1.5 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new BackgroundTask().execute();
    }

    private class BackgroundTask extends AsyncTask {
        Intent intent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            intent = new Intent(SplashActivity.this, StartIndoorActivity.class);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            /*  Use this method to load background
             * data that your app needs. */
            try {
                Thread.sleep(SPLASH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            Pass your loaded data here using Intent
//            intent.putExtra("data_key", "");
            startActivity(intent);
            finish();
        }
    }
}