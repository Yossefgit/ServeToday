// ShiftSelectionActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShiftSelectionActivity extends AppCompatActivity {

    private LinearLayout cardMorning;
    private LinearLayout cardNoon;
    private LinearLayout cardEvening;
    private LinearLayout cardNight;
    private TextView badgeMorning;
    private TextView badgeNoon;
    private TextView badgeEvening;
    private TextView badgeNight;
    private TextView textSelectedShiftSummary;
    private TextView textShiftEventTitle;
    private TextView textShiftEventDescription;
    private Button buttonAttend;
    private Button buttonAddToCalendar;
    private String selectedShift = "";
    private String eventTitle = "";
    private String eventDescription = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shift_selection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.shiftSelectionRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackShift);
        textShiftEventTitle = findViewById(R.id.textShiftEventTitle);
        textShiftEventDescription = findViewById(R.id.textShiftEventDescription);
        textSelectedShiftSummary = findViewById(R.id.textSelectedShiftSummary);
        cardMorning = findViewById(R.id.cardMorning);
        cardNoon = findViewById(R.id.cardNoon);
        cardEvening = findViewById(R.id.cardEvening);
        cardNight = findViewById(R.id.cardNight);
        badgeMorning = findViewById(R.id.badgeMorning);
        badgeNoon = findViewById(R.id.badgeNoon);
        badgeEvening = findViewById(R.id.badgeEvening);
        badgeNight = findViewById(R.id.badgeNight);
        buttonAttend = findViewById(R.id.buttonAttend);
        buttonAddToCalendar = findViewById(R.id.buttonAddToCalendar);

        eventTitle = getIntent().getStringExtra("title");
        eventDescription = getIntent().getStringExtra("description");

        if (eventTitle == null) {
            eventTitle = "Volunteer Event";
        }

        if (eventDescription == null) {
            eventDescription = "Event description unavailable.";
        }

        textShiftEventTitle.setText(eventTitle);
        textShiftEventDescription.setText(eventDescription);

        buttonBack.setOnClickListener(v -> finish());

        cardMorning.setOnClickListener(v -> selectShift("Morning", "6:00 AM - 10:00 AM"));
        cardNoon.setOnClickListener(v -> selectShift("Noon", "12:00 PM - 4:00 PM"));
        cardEvening.setOnClickListener(v -> selectShift("Evening", "4:00 PM - 6:00 PM"));
        cardNight.setOnClickListener(v -> selectShift("Night", "8:00 PM - 10:00 PM"));

        buttonAddToCalendar.setOnClickListener(v ->
                Toast.makeText(ShiftSelectionActivity.this, "Prototype only: event added to calendar", Toast.LENGTH_SHORT).show()
        );

        buttonAttend.setOnClickListener(v -> {
            if (selectedShift.isEmpty()) {
                Toast.makeText(ShiftSelectionActivity.this, "Please select a shift", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(ShiftSelectionActivity.this, RegistrationCompleteActivity.class);
            intent.putExtra("accountOnly", false);
            intent.putExtra("title", eventTitle);
            intent.putExtra("selectedShift", selectedShift);
            startActivity(intent);
        });
    }

    private void selectShift(String label, String time) {
        selectedShift = label + " (" + time + ")";
        textSelectedShiftSummary.setText("Selected Shift: " + selectedShift);

        resetCards();
        hideBadges();
        highlightSelectedCard(label);

        buttonAttend.setText("Confirm " + label + " Shift");
        buttonAddToCalendar.setVisibility(View.VISIBLE);
    }

    private void resetCards() {
        resetCard(cardMorning);
        resetCard(cardNoon);
        resetCard(cardEvening);
        resetCard(cardNight);
    }

    private void resetCard(LinearLayout card) {
        card.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        card.setElevation(3f);
    }

    private void hideBadges() {
        badgeMorning.setVisibility(TextView.GONE);
        badgeNoon.setVisibility(TextView.GONE);
        badgeEvening.setVisibility(TextView.GONE);
        badgeNight.setVisibility(TextView.GONE);
    }

    private void highlightSelectedCard(String label) {
        if (label.equals("Morning")) {
            setSelectedCard(cardMorning, badgeMorning);
        } else if (label.equals("Noon")) {
            setSelectedCard(cardNoon, badgeNoon);
        } else if (label.equals("Evening")) {
            setSelectedCard(cardEvening, badgeEvening);
        } else if (label.equals("Night")) {
            setSelectedCard(cardNight, badgeNight);
        }
    }

    private void setSelectedCard(LinearLayout card, TextView badge) {
        card.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#EDE3FF")));
        card.setElevation(8f);
        badge.setVisibility(TextView.VISIBLE);
    }
}