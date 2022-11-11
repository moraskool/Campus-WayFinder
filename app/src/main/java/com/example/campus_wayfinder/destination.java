package com.example.campus_wayfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class destination extends AppCompatActivity {
    private Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                opendByLocation();

            }
        });

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                opendByLandmark();

            }
        });
    }

    public void opendByLocation() {
        Intent intent = new Intent(this, ByLocation.class);
        startActivity(intent);
    }

    public void opendByLandmark() {
        Intent intent = new Intent(this, ByLandmark.class);
        startActivity(intent);
    }
}