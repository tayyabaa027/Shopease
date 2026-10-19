package com.example.shopease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);   // (or activity_register / activity_home)
        InsetsHelper.apply(this);                   // <-- add this line

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        Button btnLogin = findViewById(R.id.btnLoginSubmit);
        TextView tvGoRegister = findViewById(R.id.tvGoRegister);

        btnLogin.setOnClickListener(v -> loginUser());

        tvGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email");
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Enter your password");
            return;
        }

        SharedPreferences users = getSharedPreferences("users", MODE_PRIVATE);
        String savedPass = users.getString(email + "_pass", null);

        if (savedPass == null) {
            Toast.makeText(this, "No account found. Please register.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!savedPass.equals(password)) {
            etPassword.setError("Wrong password");
            return;
        }

        // ----- Success: save the session -----
        String name = users.getString(email + "_name", "User");
        getSharedPreferences("session", MODE_PRIVATE).edit()
                .putBoolean("logged_in", true)
                .putString("name", name)
                .putString("email", email)
                .apply();

        Intent intent = new Intent(this, HomeActivity.class);
        // Clear the back stack so Back doesn't return to login
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}