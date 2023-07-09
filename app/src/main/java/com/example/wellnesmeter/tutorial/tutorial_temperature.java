package com.example.wellnesmeter.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import com.example.wellnesmeter.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tutorial_temperature extends AppCompatActivity {
    Button next;
    Button tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_temperature);

        next = findViewById(R.id.nexta);
        tutorial = findViewById(R.id.tutorial);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tutorial_temperature.this, com.example.wellnesmeter.tutorial.tutorial_bp.class));
            }
        });

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://how2electronics.com/iot-ir-thermometer-using-mlx90614-esp8266-on-blynk/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}