package com.example.campuswayfinder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.unity3d.player.UnityPlayerActivity;

public class UnityARActivity extends UnityPlayerActivity {

    private String SCANNED_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                return;
            } else {
                SCANNED_LOCATION = extras.getString(StartIndoorActivity.SCANNED_LOCATION);
            }
        }

        addControlsToUnityFrame();
    }

    public void addControlsToUnityFrame() {

        GradientDrawable corner = new GradientDrawable();
        corner.setShape(GradientDrawable.RECTANGLE);
        corner.setColor(Color.parseColor("#FFC33E"));
        corner.setStroke(3, Color.parseColor("#FFC33E"));
        corner.setCornerRadius(15);
        FrameLayout layout = mUnityPlayer;

        {
            TextView navTextView = new TextView(this);
            navTextView.setX(300);
            navTextView.setY(100);
            navTextView.setTextSize(15);
            navTextView.setText("Navigating to " + "Cafe47..");

            //navTextView.setText("Navigating to " + SCANNED_LOCATION + " ..");
            navTextView.setTextColor(Color.parseColor("#FFC33E"));
            layout.addView(navTextView, 600, 100);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#FFC33E"));
            myButton.setText("Exit");
            myButton.setX(320);
            myButton.setBackground(corner);
            myButton.setTextSize(15);
            myButton.setTextColor(Color.parseColor("#FF000000"));
            myButton.setY(2000);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(mUnityPlayer != null) {
                        finish();
                        startArrived();
                    }
                    finishAffinity();
                }
            });
            layout.addView(myButton, 400, 100);

        }
    }

    @Override
    public void onBackPressed() {
        if(mUnityPlayer != null) {
            finish();
        }
        finishAffinity();
    }

    private void startArrived() {
        Intent intent = new Intent(this, ArrivedActivity.class);
        startActivity(intent);
    }
}
