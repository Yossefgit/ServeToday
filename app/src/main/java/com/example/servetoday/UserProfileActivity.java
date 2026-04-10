// UserProfileActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.userProfileRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackProfile);
        Button buttonMyEvents = findViewById(R.id.buttonMyEvents);
        Button buttonViewTotalHours = findViewById(R.id.buttonViewTotalHours);

        buttonBack.setOnClickListener(v -> finish());

        buttonMyEvents.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, MyEventsActivity.class);
            startActivity(intent);
        });

        buttonViewTotalHours.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, TotalHoursActivity.class);
            startActivity(intent);
        });
    }
}