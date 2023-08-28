package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        // Retrieve the full name and email from the intent
        String fullName = getIntent().getStringExtra("fullName");
        String email = getIntent().getStringExtra("email");

        // Set the personalized message in the TextView
        TextView messageTextView = findViewById(R.id.messageTextView);
        String message = getString(R.string.thank_you_message, fullName, email);
        messageTextView.setText(message);

        // Configure the "Return to Main Activity" button
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThankYouActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
