// TotalHoursActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TotalHoursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_total_hours);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.totalHoursRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackTotalHours);
        EditText editTextSearchHours = findViewById(R.id.editTextSearchHours);
        Button buttonSearchHours = findViewById(R.id.buttonSearchHours);
        TextView textSearchStatus = findViewById(R.id.textSearchStatus);
        TextView textNoResults = findViewById(R.id.textNoResults);
        LinearLayout cardLibraryResult = findViewById(R.id.cardLibraryResult);
        Button buttonShareHours = findViewById(R.id.buttonShareHours);

        buttonBack.setOnClickListener(v -> finish());

        buttonSearchHours.setOnClickListener(v -> {
            String query = editTextSearchHours.getText().toString().trim().toLowerCase();

            if (query.isEmpty()) {
                textSearchStatus.setText("Type an event name to search your service history.");
                cardLibraryResult.setVisibility(View.GONE);
                textNoResults.setVisibility(View.GONE);
                return;
            }

            if (query.contains("library")) {
                textSearchStatus.setText("Showing hours for Library");
                cardLibraryResult.setVisibility(View.VISIBLE);
                textNoResults.setVisibility(View.GONE);
            } else {
                textSearchStatus.setText("Search complete");
                cardLibraryResult.setVisibility(View.GONE);
                textNoResults.setVisibility(View.VISIBLE);
            }
        });

        buttonShareHours.setOnClickListener(v -> {
            Intent intent = new Intent(TotalHoursActivity.this, ExportHoursActivity.class);
            intent.putExtra("eventName", "Library");
            intent.putExtra("hoursWorked", "13 Hours 14 Minutes");
            startActivity(intent);
        });
    }
}