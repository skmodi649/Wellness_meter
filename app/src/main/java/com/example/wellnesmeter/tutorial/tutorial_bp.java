package com.example.wellnesmeter.tutorial;
import com.example.wellnesmeter.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tutorial_bp extends AppCompatActivity {
    Button start;
    Button tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_bp);

        start = findViewById(R.id.start);
        tutorial = findViewById(R.id.tutorial);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tutorial_bp.this, com.example.wellnesmeter.sensingActivity.class));
            }
        });

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.rfwireless-world.com/Terminology/Blood-Pressure-sensor-interfacing-with-Arduino-Code-and-schematic.html"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}