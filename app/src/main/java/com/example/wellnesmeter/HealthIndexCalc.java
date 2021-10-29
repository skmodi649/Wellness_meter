package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import java.lang.String;


public class HealthIndexCalc extends AppCompatActivity {
    Button calculate;
    ImageButton logu;
    EditText age;
    EditText bmi;
    EditText pulse;
    EditText bp;
    EditText sugar;
    EditText cholesterol;
    EditText sodium;
    EditText resp;
    EditText bpa;
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
        age = findViewById(R.id.hciage);
        bpa = findViewById(R.id.hcibpa);
        totalscore = 0.0;

        logu.setOnClickListener(view -> startActivity(new Intent(HealthIndexCalc.this, MainActivity.class)));

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                BMI();
                PULSE();
                SUGAR();
                CHOLESTEROL();
                SODIUM();
                RESPIRATORY();
                BLOOD_PRESSURE();

                Intent myIntent = new Intent(HealthIndexCalc.this, ResultActivity.class);
                myIntent.putExtra("key", totalscore);
                startActivity(myIntent);
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
        } else if (bmival >= 18.50 && bmival <= 25) {
            totalscore = totalscore + 10;
        } else if (bmival > 25 && bmival <= 30) {
            totalscore = totalscore + 7;
        } else if (bmival > 30 && bmival <= 40) {
            totalscore = totalscore + 5;
        } else {
            totalscore = totalscore + 4;
        }
    }

    @SuppressLint("SetTextI18n")
    public void PULSE() {
        CharSequence pulsestr = pulse.getText();
        double pulseval = Double.parseDouble(String.valueOf(pulsestr));
        pulseval = Math.round(pulseval * 100.0) / 100.0;

        if (pulseval >= 60 && pulseval <= 100)
            totalscore = totalscore + 10;
        else {
            if (pulseval < 60) {
                double pulseScore = (100 - (((60 - pulseval) / 40) * 100)) / 10;
                totalscore = totalscore + pulseScore;
            } else {
                double pulseScore = (100 - (((100 - pulseval) / 40) * 100)) / 10;
                totalscore = totalscore + pulseScore;
            }
        }
    }

    public void SUGAR() {
        CharSequence sugarstr = sugar.getText();
        double sugarval = Double.parseDouble(String.valueOf(sugarstr));
        sugarval = Math.round(sugarval * 100.0) / 100.0;

        if (sugarval >= 70 && sugarval <= 100) {
            totalscore = totalscore + 10;
        } else {
            if (sugarval > 100 && sugarval < 125) {
                totalscore = totalscore + 8;
            } else {
                totalscore = totalscore + 5;
            }
        }
    }

    public void CHOLESTEROL() {
        CharSequence agestr = age.getText();
        int ageval = Integer.parseInt(String.valueOf(agestr));
        CharSequence cholesstr = cholesterol.getText();
        double cholesval = Double.parseDouble(String.valueOf(cholesstr));
        cholesval = Math.round(cholesval * 100.0) / 100.0;

        if (ageval <= 19) {
            if (cholesval < 170) {
                totalscore = totalscore + 10;
            } else {
                double choles = (100 - (cholesval - 170)) / 10;
                totalscore = totalscore + choles;
            }
        }

        if (ageval > 19) {
            if (cholesval >= 125 && cholesval <= 200) {
                totalscore = totalscore + 10;
            } else {
                if (cholesval < 125) {
                    double choles = (100 - (125 - cholesval)) / 10;
                    totalscore = totalscore + choles;
                } else {
                    double choles = (100 - (cholesval - 200)) / 10;
                    totalscore = totalscore + choles;
                }
            }
        }
    }

    public void SODIUM() {
        CharSequence sodstr = sodium.getText();
        double sodval = Double.parseDouble(String.valueOf(sodstr));
        sodval = Math.round(sodval * 100.0) / 100.0;

        if (sodval >= 135 && sodval <= 145) {
            totalscore = totalscore + 10;
        } else {
            if (sodval < 135) {
                double sod = (100 - (135 - sodval)) / 10;
                totalscore = totalscore + sod;
            } else {
                double sod = (100 - (sodval - 145)) / 10;
                totalscore = totalscore + sod;
            }
        }
    }

    public void RESPIRATORY() {
        CharSequence respstr = resp.getText();
        double respval = Double.parseDouble(String.valueOf(respstr));
        respval = Math.round(respval * 100.0) / 100.0;

        if (respval >= 12 && respval <= 20) {
            totalscore = totalscore + 10;
        } else if (respval < 12) {
            double respa = (100 - (12 - respval) * 4) / 10;
            totalscore = totalscore + respa;
        } else if (respval > 20 && respval <= 25) {
            double respa = (100 - (respval - 20) * 4) / 10;
            totalscore = totalscore + respa;
        } else {
            double respa = (100 - (respval - 25) * 4) / 10;
            totalscore = totalscore + respa;
        }
    }

    public void BLOOD_PRESSURE() {
        CharSequence bloodstrs = bp.getText();
        CharSequence bloodstra = bpa.getText();
        double bpsys = Double.parseDouble(String.valueOf(bloodstrs));
        double bpdys = Double.parseDouble(String.valueOf(bloodstra));
        bpsys = Math.round(bpsys * 100.0) / 100.0;
        bpdys = Math.round(bpdys * 100.0) / 100.0;
        if (bpdys <= 80 && bpdys <= 120) {
            totalscore = totalscore + 10;
        }

        if ((bpsys > 120 && bpsys <= 139) && (bpdys > 80 && bpdys <= 89)) {
            totalscore = totalscore + 7;
        }

        if (bpsys > 140 && bpdys > 90) {
            totalscore = totalscore + 5;
        }
    }
}