// ExportConfirmationActivity.java
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

public class ExportConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_export_confirmation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.exportConfirmationRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackExportConfirmation);
        TextView textConfirmationMessage = findViewById(R.id.textConfirmationMessage);
        Button buttonReturnToProfile = findViewById(R.id.buttonReturnToProfile);

        String eventName = getIntent().getStringExtra("eventName");
        String hoursWorked = getIntent().getStringExtra("hoursWorked");

        if (eventName == null) {
            eventName = "your selected event";
        }

        if (hoursWorked == null) {
            hoursWorked = "0 Hours";
        }

        textConfirmationMessage.setText("Total logged hours for " + eventName + " (" + hoursWorked + ") were sent to the email on file: student@sjsu.edu");

        buttonBack.setOnClickListener(v -> finish());

        buttonReturnToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ExportConfirmationActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });
    }
}