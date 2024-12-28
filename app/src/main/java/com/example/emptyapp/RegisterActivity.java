package com.example.emptyapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailRegister, passwordRegister, passwordRegisterConf;
    private Button btnRegister;
    private TextView loginRedirect;
    //private EditText usernameRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailRegister = findViewById(R.id.email_register);
        passwordRegister = findViewById(R.id.password_register);
        passwordRegisterConf = findViewById(R.id.password_register_conf);
        btnRegister = findViewById(R.id.btn_register);
        loginRedirect = findViewById(R.id.login_redirect);
        //usernameRegister = findViewById(R.id.username);

        btnRegister.setOnClickListener(v -> {
            String email = emailRegister.getText().toString().trim();
            String password = passwordRegister.getText().toString().trim();
            String passwordConf = passwordRegisterConf.getText().toString().trim();
            String username = passwordRegisterConf.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(passwordConf)) {
                Toast.makeText(RegisterActivity.this, "Password confirmation does not match", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(email, password);
            }
        });


        loginRedirect.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mAuth.getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(emailTask -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Verification email sent. Please check your inbox.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Registration Failed";
                        Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}