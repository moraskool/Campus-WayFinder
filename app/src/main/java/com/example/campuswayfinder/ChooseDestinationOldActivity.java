package com.example.campuswayfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseDestinationOldActivity extends AppCompatActivity {

    // the location chosen by user in coordinates ??
    public static final String DESTINATION_CHOICE = "destChoice";
    private String destChoice;
    private Button goBtn, libButton, ofcsButton, cafeButton, pLButton, lecHallButton;
    private AutoCompleteTextView textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination_old);
        //libButton = findViewById(R.id.libButton);
        //
        goBtn = findViewById(R.id.searchNav);
        destChoice = "45.83, 87.89";  // change this later to actual destination coordinates

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendLibraries();
            }
        });
    }

    private void opendLibraries() {
        Intent intent = new Intent(ChooseDestinationOldActivity.this, StartOutdoorActivity.class);
        intent.putExtra(DESTINATION_CHOICE, destChoice); // pass the info to the startoutdoor activity
        startActivity(intent);
    }
}
