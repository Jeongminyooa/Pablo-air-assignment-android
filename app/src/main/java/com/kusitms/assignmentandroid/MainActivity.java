package com.kusitms.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnScanner;
    Button btnCreateQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScanner = findViewById(R.id.btn_scanner);
        btnCreateQr = findViewById(R.id.btnCreateQR);

        btnScanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

        btnCreateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateQR.class);
                startActivity(intent);
            }
        });
    }
}