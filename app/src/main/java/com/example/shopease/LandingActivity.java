package com.example.shopease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If user is already logged in, skip the landing page
        SharedPreferences session = getSharedPreferences("session", MODE_PRIVATE);
        if (session.getBoolean("logged_in", false)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_landing);

        Button btnGetStarted = findViewById(R.id.btnGetStarted);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnGetStarted.setOnClickListener(v ->
                startActivity(new Intent(LandingActivity.this, RegisterActivity.class)));

        btnLogin.setOnClickListener(v ->
                startActivity(new Intent(LandingActivity.this, LoginActivity.class)));
    }
}