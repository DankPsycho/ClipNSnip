package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button reservationButton = findViewById(R.id.button_reservation);
        reservationButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
            startActivity(intent);
        });
        Button aboutUsButton = findViewById(R.id.button_about_us);
        aboutUsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });

    }


}