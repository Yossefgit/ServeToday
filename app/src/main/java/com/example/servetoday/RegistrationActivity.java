// RegistrationActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrationRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonBack = findViewById(R.id.buttonBackRegistration);
        TextView textRegistrationTitle = findViewById(R.id.textRegistrationTitle);
        TextView textRegistrationSubtitle = findViewById(R.id.textRegistrationSubtitle);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextRetypePassword = findViewById(R.id.editTextRetypePassword);
        CheckBox checkBoxTerms = findViewById(R.id.checkBoxTerms);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        Button buttonLearnMore = findViewById(R.id.buttonLearnMore);

        boolean accountOnly = getIntent().getBooleanExtra("accountOnly", false);
        String title = getIntent().getStringExtra("title");
        String selectedShift = getIntent().getStringExtra("selectedShift");

        if (accountOnly) {
            textRegistrationTitle.setText("Create Account");
            textRegistrationSubtitle.setText("Create your Serve Today account to save events, manage shifts, and track your impact.");
            buttonSignUp.setText("Create Account");
        } else {
            if (title == null) {
                title = "Volunteer Event";
            }

            if (selectedShift == null) {
                selectedShift = "No shift selected";
            }

            textRegistrationTitle.setText("Sign Up");
            textRegistrationSubtitle.setText("Create your account for " + title + " • " + selectedShift);
            buttonSignUp.setText("Sign Up");
        }

        buttonBack.setOnClickListener(v -> finish());

        buttonLearnMore.setOnClickListener(v ->
                Toast.makeText(RegistrationActivity.this, "Prototype only: learn more screen", Toast.LENGTH_SHORT).show()
        );

        String finalTitle = title;
        String finalSelectedShift = selectedShift;
        boolean finalAccountOnly = accountOnly;

        buttonSignUp.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString();
            String retypePassword = editTextRetypePassword.getText().toString();

            if (name.isEmpty()) {
                editTextName.setError("Enter your name");
                return;
            }

            if (email.isEmpty()) {
                editTextEmail.setError("Enter your email");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Enter a valid email");
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError("Enter a password");
                return;
            }

            if (password.length() < 6) {
                editTextPassword.setError("Password must be at least 6 characters");
                return;
            }

            if (!password.equals(retypePassword)) {
                editTextRetypePassword.setError("Passwords do not match");
                return;
            }

            if (!checkBoxTerms.isChecked()) {
                Toast.makeText(RegistrationActivity.this, "Please agree to the Terms of Use and Privacy Policy", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(RegistrationActivity.this, RegistrationCompleteActivity.class);
            intent.putExtra("accountOnly", finalAccountOnly);
            intent.putExtra("title", finalTitle);
            intent.putExtra("selectedShift", finalSelectedShift);
            intent.putExtra("name", name);
            startActivity(intent);
        });
    }
}