package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    private ToggleButton toggleButton4;
    private TextView statusTextView;


    private double hairCuttingPrice = 10.0;
    private double beardTrimmingPrice = 8.0;
    private double hairWashingPrice = 5.0;
    private double stylingPrice = 5.0;
    private double totalPrice = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Button reservationButton = findViewById(R.id.button_select_services);
        reservationButton.setOnClickListener(v -> {
            // Handle button click and navigate to the ConfirmationActivity
            Intent intent = new Intent(ServicesActivity.this, TimeAndDateActivity.class);
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

        // Add onClick listeners to the toggle buttons
        toggleButton1.setOnClickListener(v -> updateStatusText());
        toggleButton2.setOnClickListener(v -> updateStatusText());
        toggleButton3.setOnClickListener(v -> updateStatusText());
        toggleButton4.setOnClickListener(v -> updateStatusText());
    }

    // Update the statusTextView text based on the toggled buttons
    private void updateStatusText() {
        StringBuilder status = new StringBuilder();
        StringBuilder selectedServices = new StringBuilder();

        totalPrice = 0.0; // Reset the total price

        if (toggleButton1.isChecked()) {
            status.append("• Shaving\n");
            selectedServices.append("Shaving, ");
            totalPrice += hairCuttingPrice;
        }
        if (toggleButton2.isChecked()) {
            status.append("• Hair Washing\n");
            selectedServices.append("Hair Washing, ");
            totalPrice += hairWashingPrice;
        }
        if (toggleButton3.isChecked()) {
            status.append("• Hair Care\n");
            selectedServices.append("Hair Care, ");
            totalPrice += stylingPrice;
        }
        if (toggleButton4.isChecked()) {
            status.append("• Beard trimming\n");
            selectedServices.append("Beard Trimming, ");
            totalPrice += beardTrimmingPrice;
        }

        if (selectedServices.length() > 0) {
            // Remove the trailing comma and space
            selectedServices.delete(selectedServices.length() - 2, selectedServices.length());

            statusTextView.setText("You selected " + selectedServices.toString() + "\nTotal Price: €" + totalPrice);
        } else {
            statusTextView.setText("Please select at least one service.");
        }
    }


    // Get the selected service
    private String getSelectedService() {
        StringBuilder selectedService = new StringBuilder();
        if (toggleButton1.isChecked()) {
            selectedService.append("Service1 (Shaving), ");
        }
        if (toggleButton2.isChecked()) {
            selectedService.append("Service2 (Hair Washing), ");
        }
        if (toggleButton3.isChecked()) {
            selectedService.append("Service3 (Hair Care), ");
        }
        if (toggleButton4.isChecked()) {
            selectedService.append("Service4 (Beard trimming), ");
        }

        // Remove the trailing comma and space
        if (selectedService.length() > 0) {
            selectedService.delete(selectedService.length() - 2, selectedService.length());
        }

        selectedService.append("\nTotal Price: $" + totalPrice); // Append total price

        return selectedService.toString();
    }

}
