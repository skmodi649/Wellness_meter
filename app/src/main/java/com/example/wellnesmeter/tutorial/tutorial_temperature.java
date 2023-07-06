package com.example.wellnesmeter.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import com.example.wellnesmeter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tutorial_temperature extends AppCompatActivity {
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_temperature);

        next = findViewById(R.id.nexta);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tutorial_temperature.this, com.example.wellnesmeter.tutorial.tutorial_bp.class));
            }
        });
    }
}