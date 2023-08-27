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

        // Find the button and set the OnClickListener using lambda expression
        Button reservationButton = findViewById(R.id.button_reservation);
        reservationButton.setOnClickListener(v -> {
            // Handle button click and navigate to the reservation screen
            Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
            startActivity(intent);
        });
    }

    // Other methods and code for your MainActivity

}