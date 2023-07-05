package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class bmiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String value = bundle.getString("value");
            TextView textView = findViewById(R.id.bmidisplay);
            textView.setText(value);
            TextView Message = findViewById(R.id.bmicategorydisplay);
            double bmi = Double.parseDouble(value);
            if(bmi < 18.50){
                Message.setText("Underweight!");}
            else if(bmi >= 18.50 && bmi <= 24.9){
                Message.setText("Normal!");}
            else if(bmi >= 25 && bmi <= 29.9) {
                Message.setText("Overweight!");}
            else {
                Message.setText("Obese!");
            }
        }

        Button proceed = findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmiActivity.this, TermsAndConditions.class));
            }
        });
    }
}