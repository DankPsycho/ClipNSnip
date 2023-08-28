package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeAndDateActivity extends AppCompatActivity {
    private RadioGroup shiftsRadioGroup;
    private int lastCheckedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_date);

        shiftsRadioGroup = findViewById(R.id.shiftsRadioGroup);
        lastCheckedId = shiftsRadioGroup.getCheckedRadioButtonId();

        // Set an OnCheckedChangeListener on the shiftsRadioGroup
        shiftsRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = findViewById(checkedId);
            selectedRadioButton.setTextColor(ContextCompat.getColor(this, android.R.color.black));

            if (lastCheckedId != -1 && lastCheckedId != checkedId) {
                RadioButton lastSelectedRadioButton = findViewById(lastCheckedId);
                lastSelectedRadioButton.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            }

            lastCheckedId = checkedId;
        });

        // Find the "Confirm Time and Date" button and set the OnClickListener
        Button confirmButton = findViewById(R.id.time_and_date_button);
        confirmButton.setOnClickListener(v -> {

            // Create an intent to navigate to the UserDetailsActivity
            Intent intent = new Intent(TimeAndDateActivity.this, UserDetailsActivity.class);
            intent.putExtra("selectedTime", getSelectedTime());
            intent.putExtra("selectedDate", getSelectedDate());
            intent.putExtra("selectedServices", getIntent().getStringExtra("selectedServices"));
            intent.putExtra("totalPrice", getIntent().getDoubleExtra("totalPrice", 0.0));

            startActivity(intent);
        });
    }

    private String formatDate(long dateInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(dateInMillis));
    }

    private String getSelectedTime(RadioGroup shiftsGroup) {
        StringBuilder selectedTime = new StringBuilder();
        int selectedRadioButtonId = shiftsGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton radioButton = findViewById(selectedRadioButtonId);
            selectedTime.append(radioButton.getText()).append(", ");
        }
        // Remove the trailing comma and space
        if (selectedTime.length() > 0) {
            selectedTime.delete(selectedTime.length() - 2, selectedTime.length());
        }
        return selectedTime.toString();
    }

    // Getter method to retrieve selected date
    public String getSelectedDate() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        long selectedDateInMillis = calendarView.getDate();
        return formatDate(selectedDateInMillis);
    }

    // Getter method to retrieve selected time
    public String getSelectedTime() {
        RadioGroup shiftsGroup = findViewById(R.id.shiftsRadioGroup);
        return getSelectedTime(shiftsGroup);
    }
}
