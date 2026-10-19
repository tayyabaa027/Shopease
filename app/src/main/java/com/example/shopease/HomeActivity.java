package com.example.shopease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setContentView(R.layout.activity_home);   // (or activity_register / activity_home)
        InsetsHelper.apply(this);                   // <-- add this line

        SharedPreferences session = getSharedPreferences("session", MODE_PRIVATE);

        TextView tvName = findViewById(R.id.tvName);
        tvName.setText(session.getString("name", "User"));

        // Category cards
        View.OnClickListener cardClick = v ->
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
        findViewById(R.id.cardElectronics).setOnClickListener(cardClick);
        findViewById(R.id.cardFashion).setOnClickListener(cardClick);
        findViewById(R.id.cardHome).setOnClickListener(cardClick);

        // Logout
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            session.edit().clear().apply();
            Intent intent = new Intent(this, LandingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}