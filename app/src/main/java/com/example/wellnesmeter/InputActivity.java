package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private TextView bmires;
    private TextView Message;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        weight = findViewById(R.id.wt);
        height = findViewById(R.id.ht);
        bmires = findViewById(R.id.bmires);
        Button result = findViewById(R.id.calres);
        Message = findViewById(R.id.message);
        Button health = findViewById(R.id.health);
        ImageButton logu = findViewById(R.id.logout);

        result.setOnClickListener(view -> {
            double wt = Double.parseDouble(String.valueOf(weight.getText()));
            double ht = Double.parseDouble(String.valueOf(height.getText()));

            double bmi = wt / (ht*ht);
            bmi = Math.round(bmi*100.0)/100.0;    // Rounding off the bmi value to two decimal places
            String res = Double.toString(bmi);

            bmires.setText(res);

            if(bmi < 18.50){
                Message.setText("Underweight!");}
            else if(bmi >= 18.50 && bmi <= 24.9){
                Message.setText("Normal!");}
               else if(bmi >= 25 && bmi <= 29.9) {
                    Message.setText("Overweight!");}
                    else {
                        Message.setText("Obese!");
                    }
                });
        health.setOnClickListener(view -> startActivity(new Intent(InputActivity.this , HealthIndexCalc.class)));

        logu.setOnClickListener(view -> startActivity(new Intent(InputActivity.this , MainActivity.class)));
    }
}