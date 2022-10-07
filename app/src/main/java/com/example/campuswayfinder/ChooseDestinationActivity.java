package com.example.campuswayfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChooseDestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);

        Button chooseDestination = findViewById(R.id.chooseDestinationButton);
        chooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseDestinationActivity.this, StartIndoorActivity.class);
                startActivity(intent);
            }
        });
    }
}
