package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tabs_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Button hic = findViewById(R.id.heal);
        Button aqi = findViewById(R.id.aqi);
        Button covid = findViewById(R.id.covid);

        hic.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , InputActivity.class)));
        covid.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , dashboard.class)));
    }
}