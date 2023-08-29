package com.example.myapplication;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

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

        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create or open the database
                DBHelper dbHelper = new DBHelper(ConfirmationActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Add reservation to the database
                dbHelper.addReservation(db, fullName, email, mobile, selectedDate, selectedTime, selectedServices, totalPrice);

                // Close the database connection
                db.close();
                // Start ThankYouActivity
                Intent intent = new Intent(ConfirmationActivity.this, ThankYouActivity.class);
                intent.putExtra("fullName",getIntent().getStringExtra("fullName"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                startActivity(intent);
            }
        });
    }
}