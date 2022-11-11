package com.example.campus_wayfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class ByLandmark extends AppCompatActivity {

    String[] lecture_halls = {"Martin Luther King Hall", "Salazr Hall", "James M. Rosser Hall", "La Kretz Hall"};
    String[] parking_lots = { "Parking Structure A",  "Parking Structure B",  "Parking Structure C", "Parking Lot 7", "Parking Lot 5", "Parking Lot 7A", "Parking Lot 1", "Parking Lot 1A", "Parking Lot 4", "Parking Lot 10", "Parking Lot 3", "Parking Lot 8", "Parking Lot 9", "Parking Lot 6", "Parking Lot 11", "Parking Lot 2"};
    String[] cafeterias = {"StarBucks","Golden Eagle Dining", "Student Union Dining", "Golden Eagle Food Court"};
    String[] offices = {"Administration", "Student Services", "University Student Union", "Admissions", "Student Affairs(SA)","Alumini Center", "Career Center(CC)", "Student Health Center(SHC)", "Public Safety/Parking/Transportation Service Center"};
    String[] libraries = {"John F. Kennedy Memorial Library", "John F. Kennedy Memorial Library - Palmer Wing", "Golden Eagle BookStore"};

    AutoCompleteTextView autoCompleteLectureHalls, autoCompleteCafeterias, autoCompleteOffices, autoCompleteLibraries, autoCompleteParkingLots;
    ArrayAdapter<String> adapterItemsLecture, adapterItemsCafeterias, adapterItemsOffices, adapterItemsLibraries, adapterItemsParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_landmark);

        autoCompleteLectureHalls = findViewById(R.id.auto_complete_lecture_halls);
        autoCompleteCafeterias = findViewById(R.id.auto_complete_cafeterias);
        autoCompleteLibraries = findViewById(R.id.auto_complete_libraries);
        autoCompleteOffices = findViewById(R.id.auto_complete_offices);
        autoCompleteParkingLots = findViewById(R.id.auto_complete_parkings);

        adapterItemsLecture = new ArrayAdapter<String>(this,R.layout.list_item, lecture_halls);
        adapterItemsCafeterias = new ArrayAdapter<String>(this, R.layout.list_item, cafeterias);
        adapterItemsLibraries = new ArrayAdapter<String>(this, R.layout.list_item, libraries);
        adapterItemsOffices = new ArrayAdapter<String>(this, R.layout.list_item, offices);
        adapterItemsParking = new ArrayAdapter<String>(this, R.layout.list_item, parking_lots);

        autoCompleteLectureHalls.setAdapter(adapterItemsLecture);
        autoCompleteCafeterias.setAdapter(adapterItemsCafeterias);
        autoCompleteLibraries.setAdapter(adapterItemsLibraries);
        autoCompleteOffices.setAdapter(adapterItemsOffices);
        autoCompleteParkingLots.setAdapter(adapterItemsParking);

        autoCompleteCafeterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+ item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteLectureHalls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+ item, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteOffices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+ item, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteLibraries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+ item, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteParkingLots.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+ item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}