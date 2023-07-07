package com.example.wellnesmeter.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import com.example.wellnesmeter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class noInternet extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(noInternet.this, com.example.wellnesmeter.Tabs_activity.class));
            }
        });
    }
}