package com.example.campuswayfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseDestinationActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5;
    TextView textView;
    ArrayList<String> arrayList, arrayList1, arrayList2, arrayList3, arrayList4, arrayList5;
    String[] string1, string2, string3, string4, string5, string6, string7, string8, string9;
    String selectedFromList;
    String test;
    Dialog dialog;

    public static final String INDOOR_LOCATION = "indoorloc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);

        textView = findViewById(R.id.text_view);
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();

        string1 = getResources().getStringArray(R.array.Libraries);
        arrayList1.addAll(Arrays.asList(string1));
        string2 = getResources().getStringArray(R.array.Parking_Lots);
        arrayList2.addAll(Arrays.asList(string2));
        string3 = getResources().getStringArray(R.array.Cafeterias);
        arrayList3.addAll(Arrays.asList(string3));
        string4 = getResources().getStringArray(R.array.Offices);
        arrayList4.addAll(Arrays.asList(string4));
        string5 = getResources().getStringArray(R.array.JFKM_Depts);
        string6 = getResources().getStringArray(R.array.JFKM_Exits);
        string7 = getResources().getStringArray(R.array.JFKM_Staff);
        string8 = getResources().getStringArray(R.array.JFKM_Others);
        string9 = getResources().getStringArray(R.array.Lecture_Halls);
        arrayList5.addAll(Arrays.asList(string9));

        arrayList.addAll(Arrays.asList(string1));
        arrayList.addAll(Arrays.asList(string2));
        arrayList.addAll(Arrays.asList(string3));
        arrayList.addAll(Arrays.asList(string4));
        arrayList.addAll(Arrays.asList(string5));
        arrayList.addAll(Arrays.asList(string6));
        arrayList.addAll(Arrays.asList(string7));
        arrayList.addAll(Arrays.asList(string8));
        arrayList.addAll(Arrays.asList(string9));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(ChooseDestinationActivity.this);

                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(1000, 1500);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(ChooseDestinationActivity.this
                        , R.layout.list_item, arrayList);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        textView.setText(adapter.getItem(position));
                        selectedFromList = (String) (listView.getItemAtPosition(position));
                        //Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                        // go to the StartIndoorActivity and pass the location choice info as intent
                       if(selectedFromList.contains("@JFK"))
                        {
                            startIndoor("Lib-Basement RM001");
                            // Call the map viev.
                            // Have user navigate from current location - chosen destination
                            startMapsIntent(selectedFromList);
                        }
                        else // else if only outdoor stage is required, go to the ArrivedActivity
                        {
                            startArrived();
                            // Call the map viev.
                            // Have user navigate from current location - chosen destination
                            startMapsIntent(selectedFromList);
                        }
                    }
                });
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(arrayList1);
            }
        });
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(arrayList3);
            }
        });
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(arrayList4);
            }
        });
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(arrayList2);
            }
        });
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(arrayList5);
            }
        });
    }

    private void startIndoor(String indoorLocation) {

        Intent intent = new Intent(this, StartIndoorActivity.class);
        intent.putExtra(INDOOR_LOCATION, indoorLocation);
        startActivity(intent);
    }

    private void startArrived() {
        Intent intent = new Intent(this, ArrivedActivity.class);
        startActivity(intent);
    }

    private void startMapsIntent(String selectedFromList) {
        Uri gmmIntentUri = null;
        Intent mapIntent;
        switch (selectedFromList) {
            case "John F. Kennedy Memorial Library":
            case "Cafe 47 @JFKMLibrary":
                gmmIntentUri = Uri.parse("google.navigation:q=34.067370490206066, -118.16748849864857&mode=w");
                break;

            case "Golden Eagle BookStore":
                gmmIntentUri = Uri.parse("google.navigation:q=34.067599347489384, -118.16848529168998&mode=w");
                break;
        }
        if(gmmIntentUri == null)
        {
            return;
        }
        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void openDialog(ArrayList<String> arrayList) {
        dialog = new Dialog(ChooseDestinationActivity.this);
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(900, 1000);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ChooseDestinationActivity.this
                , R.layout.list_item, arrayList);

        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFromList = (String) (listView.getItemAtPosition(position));
                test= adapter.getItem(position);
                //Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_LONG).show();
                dialog.dismiss();
                    startArrived();
                    startMapsIntent(selectedFromList);
            }
        });
    }

}