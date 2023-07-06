package com.example.wellnesmeter.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import com.example.wellnesmeter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class tutorial_starting extends AppCompatActivity {
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_starting);

        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tutorial_starting.this, com.example.wellnesmeter.tutorial.tutorialpulse.class));
            }
        });
    }
}