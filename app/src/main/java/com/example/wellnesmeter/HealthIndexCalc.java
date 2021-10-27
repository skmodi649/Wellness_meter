package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;


public class HealthIndexCalc extends AppCompatActivity {
    Button logout;
    Button calculate;
    EditText bmi;
    EditText pulse;
    EditText bp;
    EditText sugar;
    EditText cholesterol;
    EditText sodium;
    EditText resp;
    TextView textbmi;
    TextView textpulse;
    TextView textbp;
    TextView textsugar;
    TextView textcholesterol;
    TextView textsodium;
    TextView textresp;
    double totalscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_index_calc);


        logout = findViewById(R.id.signout);
        calculate = findViewById(R.id.score);

        bmi = findViewById(R.id.hicbmi);
        pulse = findViewById(R.id.hicpulse);
        bp = findViewById(R.id.hicbp);
        sugar = findViewById(R.id.hicsugar);
        cholesterol = findViewById(R.id.hiccholesterol);
        sodium = findViewById(R.id.hicsodium);
        resp = findViewById(R.id.hicresp);

        textbmi = findViewById(R.id.bmitext);
        textbp = findViewById(R.id.bptext);
        textpulse = findViewById(R.id.pulsetext);
        textcholesterol = findViewById(R.id.cholesteroltext);
        textsugar = findViewById(R.id.sugartext);
        textsodium = findViewById(R.id.sodiumtext);
        textresp = findViewById(R.id.resptext);

        totalscore = 0.0;

        logout.setOnClickListener(view -> startActivity(new Intent(HealthIndexCalc.this, MainActivity.class)));

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                double bmival = Double.parseDouble(bmi.getText().toString());

                if (bmival < 18.50) {
                    textbmi.setText("Underweight!");
                    totalscore = totalscore + 7;
                }
                else if (bmival >= 18.50 && bmi <= 24.9) {
                    textbmi.setText("Normal!");
                    totalscore = totalscore + 10;
                }
                else if (bmival >= 25 && bmi <= 29.9) {
                    textbmi.setText("Overweight!");
                    totalscore = totalscore + 7;
                }
                else {
                    textbmi.setText("Obese!");
                    totalscore = totalscore + 5;
                }

            }
        });
    }

}