package com.example.campuswayfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Objects;

public class StartIndoorActivity extends AppCompatActivity  {

    private String scannedLocationName;
    public static final String SCANNED_LOCATION = "sca";
    boolean isUnityARLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_indoor_stage);
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Intent originalIntent = result.getOriginalIntent();
                    if (originalIntent == null) {
                        Log.d("StartIndoorActivity", "Cancelled scan");
                    } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        Log.d("StartIndoorActivity", "Cancelled scan due to missing camera permission");
                    }
                } else {
                    Log.d("StartIndoorActivity", "Scanned");
                    scannedLocationName = result.getContents();

                    if (scannedLocationName.length() > 1) {
                        StartIndoorProcess();
                    }
                    else{
                        Toast.makeText(StartIndoorActivity.this, "Path not Found.", Toast.LENGTH_LONG).show();
                    }
                }
            });
    public void scanBarcode(View view) {

        ScanOptions options = new ScanOptions();
        options.setOrientationLocked(true);
        options.setBeepEnabled(false);
        options.setPrompt("Scan QR Code for steps");
        options.setCaptureActivity(CustomScannerActivity.class);
        barcodeLauncher.launch(new ScanOptions());
    }
    private void StartIndoorProcess() {
        isUnityARLoaded = true;
        Intent intent = new Intent(getApplicationContext(), UnityARActivity.class); // call unityactivity here
        intent.putExtra(SCANNED_LOCATION, scannedLocationName);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) isUnityARLoaded = false;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
