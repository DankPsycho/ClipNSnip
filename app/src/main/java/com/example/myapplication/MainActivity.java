package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_reservation);
        startButton.setOnClickListener(v -> {
            // Handle button click and navigate to the ReservationActivity
            Intent intent = new Intent(MainActivity.this, ReservationActivity.class);
            startActivity(intent);
        });
    }
}
