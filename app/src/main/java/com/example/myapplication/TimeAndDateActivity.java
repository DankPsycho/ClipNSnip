package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeAndDateActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private RadioGroup shiftsRadioGroup;
    private int lastCheckedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_date);

        // Get the selected service from the intent extras
        String selectedService = getIntent().getStringExtra("selectedService");

        // Initialize the DBHelper
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        // Find the "Confirm Reservation" button and set the OnClickListener
        Button confirmationButton = findViewById(R.id.confirmation_button);
        confirmationButton.setOnClickListener(v -> {
            // Get the selected date from the calendar view
            CalendarView calendarView = findViewById(R.id.calendarView);
            long selectedDateInMillis = calendarView.getDate();
            // Convert the selected date to a desired format if needed
            String selectedDate = formatDate(selectedDateInMillis);

            // Get the selected time from the selected radio button
            String selectedTime = getSelectedTime(shiftsRadioGroup);

            // Insert the reservation into the SQLite table
            dbHelper.addReservation(database, selectedDate, selectedTime, selectedService);

            // Show a toast message for confirmation
            Toast.makeText(this, "Reservation confirmed", Toast.LENGTH_SHORT).show();
        });

        // Find the shifts radio group and assign it to the shiftsRadioGroup variable
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        if (database != null && database.isOpen()) {
            database.close();
        }
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
}
