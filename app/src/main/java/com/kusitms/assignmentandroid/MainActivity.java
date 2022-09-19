package com.kusitms.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCreateQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateQr = findViewById(R.id.btnCreateQR);

        btnCreateQr.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateQR.class);
            startActivity(intent);
        });
    }
}