// EventDetailActivity.java
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

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.eventDetailRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackDetail);
        TextView textEventTitle = findViewById(R.id.textEventTitle);
        TextView textEventDescription = findViewById(R.id.textEventDescription);
        TextView textEventDate = findViewById(R.id.textEventDate);
        TextView textEventTime = findViewById(R.id.textEventTime);
        TextView textEventLocation = findViewById(R.id.textEventLocation);
        TextView textEventRequirements = findViewById(R.id.textEventRequirements);
        Button buttonVolunteer = findViewById(R.id.buttonVolunteer);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String location = getIntent().getStringExtra("location");
        String requirements = getIntent().getStringExtra("requirements");

        if (title == null) {
            title = "Event Details";
        }

        if (description == null) {
            description = "Event description unavailable.";
        }

        if (date == null) {
            date = "Not available";
        }

        if (time == null) {
            time = "Not available";
        }

        if (location == null) {
            location = "Not available";
        }

        if (requirements == null) {
            requirements = "No additional requirements listed.";
        }

        textEventTitle.setText(title);
        textEventDescription.setText(description);
        textEventDate.setText("Date: " + date);
        textEventTime.setText("Time: " + time);
        textEventLocation.setText("Location: " + location);
        textEventRequirements.setText(requirements);

        buttonBack.setOnClickListener(v -> finish());

        String finalTitle = title;
        String finalDescription = description;

        buttonVolunteer.setOnClickListener(v -> {
            Intent intent = new Intent(EventDetailActivity.this, ShiftSelectionActivity.class);
            intent.putExtra("title", finalTitle);
            intent.putExtra("description", finalDescription);
            startActivity(intent);
        });
    }
}