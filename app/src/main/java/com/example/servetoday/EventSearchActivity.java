package com.example.servetoday;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventSearchActivity extends AppCompatActivity {

    private EditText editTextZipCode;
    private CalendarView calendarView;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_search);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.eventSearchRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView buttonBack = findViewById(R.id.buttonBack);
        editTextZipCode = findViewById(R.id.editTextZipCode);
        calendarView = findViewById(R.id.calendarView);
        Button buttonSubmitSearch = findViewById(R.id.buttonSubmitSearch);

        buttonBack.setOnClickListener(v -> finish());

        long dateMillis = calendarView.getDate();
        selectedDate = android.text.format.DateFormat.format("MM/dd/yyyy", dateMillis).toString();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            int displayMonth = month + 1;
            selectedDate = String.format("%02d/%02d/%04d", displayMonth, dayOfMonth, year);
        });

        buttonSubmitSearch.setOnClickListener(v -> {
            String zipCode = editTextZipCode.getText().toString().trim();

            if (zipCode.isEmpty()) {
                editTextZipCode.setError("Enter a zip code");
                return;
            }

            if (zipCode.length() != 5) {
                editTextZipCode.setError("Zip code must be 5 digits");
                return;
            }

            Toast.makeText(
                    EventSearchActivity.this,
                    "Searching events for " + zipCode + " on " + selectedDate,
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}