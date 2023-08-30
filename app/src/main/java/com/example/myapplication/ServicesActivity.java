package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class ServicesActivity extends AppCompatActivity {

    public ToggleButton toggleButton1;
    public ToggleButton toggleButton2;
    public ToggleButton toggleButton3;
    public ToggleButton toggleButton4;
    private TextView statusTextView;

    private double hairCuttingPrice = 10.0;
    private double beardTrimmingPrice = 8.0;
    private double hairWashingPrice = 5.0;
    private double stylingPrice = 5.0;
    private double totalPrice = 0.0;
    private StringBuilder selectedServices = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Button reservationButton = findViewById(R.id.button_select_services);
        reservationButton.setOnClickListener(v -> {

            // Handle button click and navigate to the TimeAndDateActivity
            Intent intent = new Intent(ServicesActivity.this, TimeAndDateActivity.class);
            // Put data in the intent
            intent.putExtra("selectedServices", getSelectedServicesString());
            intent.putExtra("totalPrice", getTotalPrice());

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
    public void updateStatusText() {
        selectedServices.setLength(0); // Clear the previous selections
        totalPrice = 0.0; // Reset the total price

        if (toggleButton1.isChecked()) {
            selectedServices.append("Hair Cutting (10€), ");
            totalPrice += hairCuttingPrice;
        }
        if (toggleButton2.isChecked()) {
            selectedServices.append("Beard Trimming (8€), ");
            totalPrice += beardTrimmingPrice;
        }
        if (toggleButton3.isChecked()) {
            selectedServices.append("Hair Washing (5€), ");
            totalPrice += hairWashingPrice;
        }
        if (toggleButton4.isChecked()) {
            selectedServices.append("Styling (5€), ");
            totalPrice += stylingPrice;
        }

        if (selectedServices.length() > 0) {
            // Remove the trailing comma and space
            selectedServices.delete(selectedServices.length() - 2, selectedServices.length());

            statusTextView.setText("You selected " + selectedServices.toString() + "\nTotal Price: €" + totalPrice);
        } else {
            statusTextView.setText("Please select at least one service.");
        }
    }

    // Getter method to retrieve the selected services string
    public String getSelectedServicesString() {
        return selectedServices.toString();
    }

    // Getter method to retrieve the total price
    public double getTotalPrice() {
        return totalPrice;
    }
    //for testing
    public String getStatusText() {
        return statusTextView.getText().toString();
    }
}
