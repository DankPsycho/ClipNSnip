package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Button reservationButton = findViewById(R.id.button_reservation);
        reservationButton.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, ServicesActivity.class);
            startActivity(intent);
        });
    }
}
