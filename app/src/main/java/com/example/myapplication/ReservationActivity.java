package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    private ToggleButton toggleButton4;
    private TextView statusTextView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Button reservationButton = findViewById(R.id.button_select_services);
        reservationButton.setOnClickListener(v -> {
            // Handle button click and navigate to the ConfirmationActivity
            Intent intent = new Intent(ReservationActivity.this, ConfirmationActivity.class);
            intent.putExtra("selectedService", getSelectedService());
            startActivity(intent);
        });

        toggleButton1 = findViewById(R.id.toggleButton1);
        toggleButton2 = findViewById(R.id.toggleButton2);
        toggleButton3 = findViewById(R.id.toggleButton3);
        toggleButton4 = findViewById(R.id.toggleButton4);
        statusTextView = findViewById(R.id.statusTextView);

        // Set initial text of the statusTextView
        updateStatusText();

        // Initialize the DBHelper
        dbHelper = new DBHelper(this);

        // Add onClick listeners to the toggle buttons
        toggleButton1.setOnClickListener(v -> updateStatusText());
        toggleButton2.setOnClickListener(v -> updateStatusText());
        toggleButton3.setOnClickListener(v -> updateStatusText());
        toggleButton4.setOnClickListener(v -> updateStatusText());
    }

    // Update the statusTextView text based on the toggled buttons
    private void updateStatusText() {
        StringBuilder status = new StringBuilder();
        if (toggleButton1.isChecked()) {
            status.append("ToggleButton1 is ON\n");
        } else {
            status.append("ToggleButton1 is OFF\n");
        }
        if (toggleButton2.isChecked()) {
            status.append("ToggleButton2 is ON\n");
        } else {
            status.append("ToggleButton2 is OFF\n");
        }
        if (toggleButton3.isChecked()) {
            status.append("ToggleButton3 is ON\n");
        } else {
            status.append("ToggleButton3 is OFF\n");
        }
        if (toggleButton4.isChecked()) {
            status.append("ToggleButton4 is ON\n");
        } else {
            status.append("ToggleButton4 is OFF\n");
        }

        statusTextView.setText(status.toString());
    }

    // Get the selected service
    private String getSelectedService() {
        StringBuilder selectedService = new StringBuilder();
        if (toggleButton1.isChecked()) {
            selectedService.append("Service1, ");
        }
        if (toggleButton2.isChecked()) {
            selectedService.append("Service2, ");
        }
        if (toggleButton3.isChecked()) {
            selectedService.append("Service3, ");
        }
        if (toggleButton4.isChecked()) {
            selectedService.append("Service4, ");
        }

        // Remove the trailing comma and space
        if (selectedService.length() > 0) {
            selectedService.delete(selectedService.length() - 2, selectedService.length());
        }

        return selectedService.toString();
    }
}
