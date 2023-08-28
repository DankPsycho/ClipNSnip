package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class UserDetailsActivity extends AppCompatActivity {
    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Find the UI elements
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        buttonNext = findViewById(R.id.button_confirm_details);

        // Set OnClickListener for the "Next" button
        buttonNext.setOnClickListener(v -> {
            // Get user details from the EditText fields
            String fullName = editTextFullName.getText().toString();
            String email = editTextEmail.getText().toString();
            String mobile = editTextMobile.getText().toString();

            // Create an intent to navigate to the ConfirmationActivity
            Intent intent = new Intent(UserDetailsActivity.this, ConfirmationActivity.class);
            intent.putExtra("fullName",getFullName());
            intent.putExtra("email",getEmail());
            intent.putExtra("mobile",getMobile());
            intent.putExtra("selectedTime",getIntent().getStringExtra("selectedTime"));
            intent.putExtra("selectedDate", getIntent().getStringExtra("selectedDate"));
            intent.putExtra("selectedServices", getIntent().getStringExtra("selectedServices"));
            intent.putExtra("totalPrice", getIntent().getDoubleExtra("totalPrice", 0.0));
            startActivity(intent);
        });
    }

    // Getter method to retrieve full name
    public String getFullName() {
        return editTextFullName.getText().toString();
    }

    // Getter method to retrieve email
    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    // Getter method to retrieve mobile number
    public String getMobile() {
        return editTextMobile.getText().toString();
    }
}
