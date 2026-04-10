// MyEventsActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_events);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myEventsRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackMyEvents);
        Button buttonCancelEvents = findViewById(R.id.buttonCancelEvents);
        Button buttonEditShifts = findViewById(R.id.buttonEditShifts);

        buttonBack.setOnClickListener(v -> finish());

        buttonCancelEvents.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyEventsActivity.this);
            builder.setTitle("Cancel Event");
            builder.setMessage("Are you sure you want to cancel this upcoming volunteer shift?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                Intent intent = new Intent(MyEventsActivity.this, CancellationCompleteActivity.class);
                startActivity(intent);
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        buttonEditShifts.setOnClickListener(v ->
                Toast.makeText(MyEventsActivity.this, "Prototype only: edit shift screen", Toast.LENGTH_SHORT).show()
        );
    }
}