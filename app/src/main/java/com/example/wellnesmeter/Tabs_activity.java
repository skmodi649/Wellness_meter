package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Tabs_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Button hic = findViewById(R.id.heal);
        Button aqi = findViewById(R.id.aqi);
        Button covid = findViewById(R.id.covid);
        mAuth = FirebaseAuth.getInstance();

        hic.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , InputActivity.class)));
        covid.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , dashboard.class)));
        aqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://nodejs-health-index-calculator.herokuapp.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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