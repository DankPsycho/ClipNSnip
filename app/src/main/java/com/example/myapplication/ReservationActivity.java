package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Find the button and set the OnClickListener using lambda expression
        Button reservationButton = findViewById(R.id.button_select_services);
        reservationButton.setOnClickListener(v -> {
            // Handle button click and navigate to the reservation screen
            Intent intent = new Intent(ReservationActivity.this, ConfirmationActivity.class);
            startActivity(intent);
        });

    }

    // Other methods and code for your ReservationActivity

}