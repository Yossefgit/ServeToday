// ExportHoursActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExportHoursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_export_hours);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.exportHoursRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackExportHours);
        TextView textExportEventName = findViewById(R.id.textExportEventName);
        TextView textExportHoursValue = findViewById(R.id.textExportHoursValue);
        Button buttonExportEmail = findViewById(R.id.buttonExportEmail);
        Button buttonDownloadPdf = findViewById(R.id.buttonDownloadPdf);

        String eventName = getIntent().getStringExtra("eventName");
        String hoursWorked = getIntent().getStringExtra("hoursWorked");

        if (eventName == null) {
            eventName = "Event";
        }

        if (hoursWorked == null) {
            hoursWorked = "0 Hours";
        }

        textExportEventName.setText(eventName);
        textExportHoursValue.setText(hoursWorked);

        buttonBack.setOnClickListener(v -> finish());

        String finalEventName = eventName;
        String finalHoursWorked = hoursWorked;

        buttonExportEmail.setOnClickListener(v -> {
            Intent intent = new Intent(ExportHoursActivity.this, ExportConfirmationActivity.class);
            intent.putExtra("eventName", finalEventName);
            intent.putExtra("hoursWorked", finalHoursWorked);
            startActivity(intent);
        });

        buttonDownloadPdf.setOnClickListener(v ->
                Toast.makeText(ExportHoursActivity.this, "Prototype only: PDF downloaded", Toast.LENGTH_SHORT).show()
        );
    }
}