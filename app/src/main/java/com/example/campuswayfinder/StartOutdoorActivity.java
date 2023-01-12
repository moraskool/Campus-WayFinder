package com.example.campuswayfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StartOutdoorActivity extends AppCompatActivity {

    public static final String INDOOR_LOCATION = "indoorloc";
    private String PASSED_DESTINATION;
    private String indoorLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdoor);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                PASSED_DESTINATION = null;
            } else {
                PASSED_DESTINATION = extras.getString(ChooseDestinationOldActivity.DESTINATION_CHOICE);
            }
        }
        beginOutDoorNavigation();
    }

    private void beginOutDoorNavigation() {
        // Call the map viev.
        // Have user navigate from current location - chosen destination
        // Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
        // Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // mapIntent.setPackage("com.google.android.apps.maps");
        // startActivity(mapIntent);

        // if outdoor only is required, go to the ArrivedActivity
        // TODO: call the intent for above here.

        indoorLocation = "Lib-JFK-GroundFloor"; // parse the final indoor destination
        // else go to the StartIndoorActivity and pass the location choice info as intent
        Intent intent = new Intent(StartOutdoorActivity.this, StartIndoorActivity.class);
        intent.putExtra(INDOOR_LOCATION, indoorLocation);
        startActivity(intent);
    }

}
