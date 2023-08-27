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

            // Store in database here!!!

            // Navigate to the ServicesActivity
            Intent intent = new Intent(UserDetailsActivity.this, ServicesActivity.class);
            startActivity(intent);
        });
    }
}
