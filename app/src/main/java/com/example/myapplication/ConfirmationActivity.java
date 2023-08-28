package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Retrieve data from Intent extras
        String fullName = getIntent().getStringExtra("fullName");
        String email = getIntent().getStringExtra("email");
        String mobile = getIntent().getStringExtra("mobile");
        String selectedServices = getIntent().getStringExtra("selectedServices");
        Double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);
        String selectedDate = getIntent().getStringExtra("selectedDate");
        String selectedTime = getIntent().getStringExtra("selectedTime");

        // Update TextViews with the retrieved data
        TextView fullNameTextView = findViewById(R.id.fullNameTextView);
        fullNameTextView.setText("Full Name: " + fullName);

        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText("Email: " + email);

        TextView mobileTextView = findViewById(R.id.mobileTextView);
        mobileTextView.setText("Phone: " + mobile);

        TextView selectedServicesTextView = findViewById(R.id.selectedServicesTextView);
        selectedServicesTextView.setText("Selected Services:\n" + selectedServices);

        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText("Total Price: " + totalPrice + '€');

        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
        selectedDateTextView.setText("Date: " + selectedDate);

        TextView selectedTimeTextView = findViewById(R.id.selectedTimeTextView);
        selectedTimeTextView.setText("Time: " + selectedTime);

        // Rest of your code...
    }

}
