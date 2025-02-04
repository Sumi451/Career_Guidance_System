package com.example.careerguidancesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText feedbackInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        // Initialize UI elements
        ratingBar = findViewById(R.id.ratingBar);
        feedbackInput = findViewById(R.id.feedback_input);
        submitButton = findViewById(R.id.submit_button);

        // Handle submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                String feedbackText = feedbackInput.getText().toString().trim();

                // Process feedback (you can store it in a database if needed)
                Toast.makeText(Feedback.this,
                        "Feedback submitted successfully!",
                        Toast.LENGTH_SHORT).show();

                // Navigate back to the home screen (MainActivity)
                Intent intent = new Intent(Feedback.this, Homescreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the feedback activity
            }
        });
    }
}

