package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Tabs_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Button hic = findViewById(R.id.score);
        Button health_history = findViewById(R.id.sensing);
        mAuth = FirebaseAuth.getInstance();

        hic.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , InputActivity.class)));
        health_history.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , com.example.wellnesmeter.health_history.class)));
    }
     @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(Tabs_activity.this, MainActivity.class));
            finish();
        }
    }
}