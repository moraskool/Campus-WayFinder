package com.example.campuswayfinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CustomScannerActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_barcode_scan);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}