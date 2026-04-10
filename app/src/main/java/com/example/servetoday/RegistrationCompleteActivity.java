// RegistrationCompleteActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_complete);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrationCompleteRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackComplete);
        TextView textCompleteTitle = findViewById(R.id.textCompleteTitle);
        TextView textCompleteSubtitle = findViewById(R.id.textCompleteSubtitle);
        TextView textCompleteMessage = findViewById(R.id.textCompleteMessage);
        Button buttonPrimaryAction = findViewById(R.id.buttonPrimaryAction);
        Button buttonSecondaryAction = findViewById(R.id.buttonSecondaryAction);

        boolean accountOnly = getIntent().getBooleanExtra("accountOnly", false);
        String title = getIntent().getStringExtra("title");
        String selectedShift = getIntent().getStringExtra("selectedShift");
        String name = getIntent().getStringExtra("name");

        if (title == null) {
            title = "Volunteer Event";
        }

        if (selectedShift == null) {
            selectedShift = "Selected shift";
        }

        if (name == null) {
            name = "";
        }

        if (accountOnly) {
            textCompleteTitle.setText("Account Created");
            textCompleteSubtitle.setText("Welcome, " + name + "!");
            textCompleteMessage.setText("Your Serve Today account is ready. You can now search for local events, sign up for shifts, and manage your volunteer activity.");
            buttonPrimaryAction.setText("Find Events");
            buttonSecondaryAction.setText("Go To Profile");
        } else {
            textCompleteTitle.setText("Registration Complete");

            if (name.trim().isEmpty()) {
                textCompleteSubtitle.setText("You are registered for " + title);
            } else {
                textCompleteSubtitle.setText("Thanks, " + name + "! You are registered for " + title);
            }

            textCompleteMessage.setText("Selected Shift: " + selectedShift + "\n\nYour volunteer spot has been saved. You can manage this event later from your profile.");
            buttonPrimaryAction.setText("Go To Profile");
            buttonSecondaryAction.setText("Find More Events");
        }

        buttonBack.setOnClickListener(v -> finish());

        buttonPrimaryAction.setOnClickListener(v -> {
            if (accountOnly) {
                Intent intent = new Intent(RegistrationCompleteActivity.this, EventSearchActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(RegistrationCompleteActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        buttonSecondaryAction.setOnClickListener(v -> {
            if (accountOnly) {
                Intent intent = new Intent(RegistrationCompleteActivity.this, UserProfileActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(RegistrationCompleteActivity.this, EventSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}