package com.example.emptyapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "1234";
    EditText usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        TextView resultText = findViewById(R.id.resultText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameInput.getText().toString().trim();
                String enteredPassword = passwordInput.getText().toString().trim();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    resultText.setTextColor(Color.RED);
                    resultText.setText("Please fill in both fields");
                } else if (enteredUsername.equals(CORRECT_USERNAME) && enteredPassword.equals(CORRECT_PASSWORD)) {
                    resultText.setTextColor(Color.GREEN);
                    resultText.setText("Correct");
                } else {
                    resultText.setTextColor(Color.RED);
                    resultText.setText("Incorrect username or password");
                }

                usernameInput.setText("");
                passwordInput.setText("");
            }
        });
    }
}
