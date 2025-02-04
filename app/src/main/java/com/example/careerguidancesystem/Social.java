package com.example.careerguidancesystem;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Social extends AppCompatActivity {

    private ImageView whatsappIcon, facebookIcon, emailIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social);

        // Initialize UI elements
        whatsappIcon = findViewById(R.id.whatsapp_icon);
        facebookIcon = findViewById(R.id.facebook_icon);
        emailIcon = findViewById(R.id.email_icon);

        // Set onClick listeners for icons
        whatsappIcon.setOnClickListener(v -> openWhatsApp());
        facebookIcon.setOnClickListener(v -> openFacebook());
        emailIcon.setOnClickListener(v -> sendEmail());
    }

    // Open WhatsApp
    private void openWhatsApp() {
        String url = "https://wa.me/1234567890";  // Replace with your WhatsApp number
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setPackage("com.whatsapp");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }

    // Open Facebook Page
    private void openFacebook() {
        String url = "https://www.facebook.com/YourPageName";  // Replace with your Facebook page
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Facebook app not installed.", Toast.LENGTH_SHORT).show();
        }
    }

    // Send Email
    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:your_email@example.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Us");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

