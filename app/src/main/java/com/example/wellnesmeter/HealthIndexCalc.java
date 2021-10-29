package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.String;


public class HealthIndexCalc extends AppCompatActivity {
    Button calculate;
    ImageButton logu;
    EditText bmi;
    EditText pulse;
    EditText bp;
    EditText sugar;
    EditText cholesterol;
    EditText sodium;
    EditText resp;
    double totalscore;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_index_calc);


        calculate = findViewById(R.id.score);
        logu = findViewById(R.id.loguhealth);
        bmi = findViewById(R.id.hicbmi);
        pulse = findViewById(R.id.hicpulse);
        bp = findViewById(R.id.hicbp);
        sugar = findViewById(R.id.hicsugar);
        cholesterol = findViewById(R.id.hiccholesterol);
        sodium = findViewById(R.id.hicsodium);
        resp = findViewById(R.id.hicresp);

        totalscore = 0.0;

        logu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthIndexCalc.this , MainActivity.class));
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                BMI();
                PULSE();

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void BMI() {
        CharSequence bmistr = bmi.getText();

        double bmival = Double.parseDouble(String.valueOf(bmistr));
        bmival = Math.round(bmival * 100.0) / 100.0;
        if (bmival < 18.50) {
            totalscore = totalscore + 7;
        } else if (bmival >= 18.50 && bmival <= 24.9) {
            totalscore = totalscore + 10;
        } else if (bmival >= 25 && bmival <= 29.9) {
            totalscore = totalscore + 7;
        } else {
            totalscore = totalscore + 5;
        }
    }

    @SuppressLint("SetTextI18n")
    public void PULSE()
    {
        CharSequence pulsestr = pulse.getText();
        double pulseval = Double.parseDouble(String.valueOf(pulsestr));
        pulseval = Math.round(pulseval * 100.0) / 100.0;

        if(pulseval>=49 && pulseval <=55) {
            totalscore = totalscore + 10;
        }
        else if (pulseval >= 56 && pulseval <= 61){
            totalscore = totalscore + 9;
        }
        else if (pulseval >= 62 && pulseval <= 65){
            totalscore = totalscore + 8;
        }
        else if(pulseval >= 66 && pulseval <= 69){
            totalscore = totalscore + 7;
        }
        else if(pulseval >= 70 && pulseval <= 73){
            totalscore = totalscore + 6;
        }
        else if(pulseval >= 74 && pulseval <= 84){
            totalscore = totalscore + 5;
        }
        else{
            totalscore = totalscore + 4;
        }
    }
    }