package com.example.wellnesmeter;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;


public class HealthIndexCalc extends AppCompatActivity {
    Button calculate;
    EditText age;
    EditText bmi;
    EditText pulse;
    EditText bp;
    EditText sugar;
    EditText cholesterol;
    EditText sodium;
    EditText resp;
    EditText bpa;
    EditText hcicalcium;
    double totalscore;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_index_calc);

        calculate = findViewById(R.id.score);
        bmi = findViewById(R.id.hicbmi);
        pulse = findViewById(R.id.hicpulse);
        bp = findViewById(R.id.hicbp);
        sugar = findViewById(R.id.hicsugar);
        cholesterol = findViewById(R.id.hiccholesterol);
        sodium = findViewById(R.id.hicsodium);
        resp = findViewById(R.id.hicresp);
        age = findViewById(R.id.hciage);
        bpa = findViewById(R.id.hcibpa);
        hcicalcium = findViewById(R.id.hiccalcium);
        totalscore = 0.0;



                calculate.setOnClickListener(view -> {
                    double totbmi = BMI();
                    double totpulse = PULSE();
                    double totsugar = SUGAR();
                    double totcholes = CHOLESTEROL();
                    double totsod = SODIUM();
                    double totcal = CALCIUM();
                    double totresp = RESPIRATORY();
                    double totbp = BLOOD_PRESSURE();

                    if (totbp != 0 && totbmi != 0 && totpulse != 0 && totsugar != 0 && totcholes != 0 && totsod != 0 && totresp != 0 && totcal != 0) {
                        totalscore = totbmi + totpulse + totsugar + totcholes + totsod + totresp + totbp + totcal;
                        String scare = Double.toString(totalscore);
                        String sbmi = Double.toString(totbmi);
                        String spulse = Double.toString(totpulse);
                        String ssugar = Double.toString(totsugar);
                        String scholes = Double.toString(totcholes);
                        String ssod= Double.toString(totsod);
                        String scal = Double.toString(totcal);
                        String sresp = Double.toString(totresp);
                        String sbp = Double.toString(totbp);
                        Intent myIntent = new Intent(HealthIndexCalc.this , improvedResult.class);
                        Bundle b = new Bundle();
                        b.putString("key" , scare);
                        b.putString("bmi" , sbmi);
                        b.putString("pulse" , spulse);
                        b.putString("sugar" , ssugar);
                        b.putString("cholesterol" , scholes);
                        b.putString("sodium" , ssod);
                        b.putString("respiratory" , sresp);
                        b.putString("bp" , sbp);
                        b.putString("calcium", scal);

                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }

                    else{
                        Toast.makeText(HealthIndexCalc.this, "Check the data entered!!", Toast.LENGTH_LONG).show();
                    }
                });
        }

    @SuppressLint("SetTextI18n")
    public  double BMI() {
        int totbmi;
        String bmistr = bmi.getText().toString().trim();
        if(bmistr.isEmpty()){
            bmi.setError("BMI is required!");
            bmi.requestFocus();
            return 0;
        }
        double bmival = Double.parseDouble(bmistr);
        bmival = Math.round(bmival * 100.0) / 100.0;
        if (bmival < 18.50) {
             return 7;
        } else if (bmival >= 18.50 && bmival <= 25) {
            return 10;
        } else if (bmival > 25 && bmival <= 30) {
            return 7;
        } else if (bmival > 30 && bmival <= 40) {
            return 5;
        } else {
            return 4;
        }
    }

    @SuppressLint("SetTextI18n")
    public double PULSE() {
        String pulsestr = pulse.getText().toString().trim();

        if(pulsestr.isEmpty()){
            pulse.setError("Pulse rate is required!");
            pulse.requestFocus();
            return 0;
        }

        double pulseval = Double.parseDouble(pulsestr);
        pulseval = Math.round(pulseval * 100.0) / 100.0;

        if (pulseval >= 60 && pulseval <= 100){
            return 10;}
        else {
            if (pulseval < 60) {
                return (100 - ((60 - pulseval) / 40 * 100)) / 10;
            } else {
                return (100 - ((pulseval - 100) / 40 * 100)) / 10;
            }
        }
    }

    public double SUGAR() {
        String sugarstr = sugar.getText().toString().trim();

        if(sugarstr.isEmpty()){
            sugar.setError("Sugar level is required!");
            sugar.requestFocus();
            return 0;
        }

        double sugarval = Double.parseDouble(sugarstr);
        sugarval = Math.round(sugarval * 100.0) / 100.0;

        if (sugarval >= 70 && sugarval <= 100) {
            return 10;
        } else {
            if (sugarval > 100 && sugarval < 125) {
                return 8;
            } else {
                return 5;
            }
        }
    }

    public double CHOLESTEROL() {
        String agestr = age.getText().toString().trim();

        if(agestr.isEmpty()){
            age.setError("Age is required!");
            age.requestFocus();
            return 0;
        }


        int ageval = Integer.parseInt(agestr);
        String cholesstr = cholesterol.getText().toString().trim();

        if(cholesstr.isEmpty()){
            cholesterol.setError("Cholesterol level is required!");
            cholesterol.requestFocus();
            return 0;
        }


        double cholesval = Double.parseDouble(cholesstr);
        cholesval = Math.round(cholesval * 100.0) / 100.0;

        if (ageval <= 19) {
            if (cholesval < 170) {
                return 10;
            } else {
                return (100 - (cholesval - 170)) / 10;
            }
        }

        else {
            if (cholesval >= 125 && cholesval <= 200) {
                return 10;
            } else {
                if (cholesval < 125) {
                    return (100 - (125 - cholesval)) / 10;
                } else {
                    return (100 - (cholesval - 200)) / 10;
                }
            }
        }
    }

    public double SODIUM() {
        String sodstr = sodium.getText().toString().trim();

        if(sodstr.isEmpty()){
            sodium.setError("Sodium level is required!");
            sodium.requestFocus();
            return 0;
        }

        double sodval = Double.parseDouble(sodstr);
        sodval = Math.round(sodval * 100.0) / 100.0;

        if (sodval >= 135 && sodval <= 145) {
            return 10;
        } else {
            if (sodval < 135) {
                return (100 - (135 - sodval)) / 10;
            } else {
                return (100 - (sodval - 145)) / 10;
            }
        }
    }

    public double CALCIUM() {
        String calstr = hcicalcium.getText().toString().trim();

        if(calstr.isEmpty()){
            hcicalcium.setError("Sodium level is required!");
            hcicalcium.requestFocus();
            return 0;
        }

        double calval = Double.parseDouble(calstr);
        calval = Math.round(calval * 100.0) / 100.0;

        if (calval >= 8.6 && calval <= 10.3) {
            return 10;
        } else {
            return 6;
        }
    }

    public double RESPIRATORY() {
        String respstr = resp.getText().toString().trim();

        if(respstr.isEmpty()){
            resp.setError("Respiratory level is required!");
            resp.requestFocus();
            return 0;
        }

        double respval = Double.parseDouble(respstr);
        respval = Math.round(respval * 100.0) / 100.0;

        if (respval >= 12 && respval <= 20) {
            return 10;
        } else if (respval < 12) {
            return (100 - (12 - respval) * 4) / 10;
        } else if (respval > 20 && respval <= 25) {
            return (100 - (respval - 20) * 4) / 10;
        } else {
            return (100 - (respval - 25) * 4) / 10;
        }
    }

    public double BLOOD_PRESSURE() {

        String bloodstrs = bp.getText().toString().trim();
        String bloodstra = bpa.getText().toString().trim();

        if(bloodstrs.isEmpty()){
            bp.setError("Systolic bp is required!");
            bpa.setError("Diastolic bp is required!");
            bp.requestFocus();
            bpa.requestFocus();
            return 0;
        }


        if(bloodstra.isEmpty()){
            bpa.setError("Diastolic bp is required!");
            bpa.requestFocus();
            return 0;
        }

        double bpsys = Double.parseDouble(bloodstrs);
        double bpdys = Double.parseDouble(bloodstra);
        bpsys = Math.round(bpsys * 100.0) / 100.0;
        bpdys = Math.round(bpdys * 100.0) / 100.0;
        if (bpdys <= 80 && bpsys <= 120) {
            return 10;
        }

        else if ((bpsys > 120 && bpsys <= 139) && (bpdys > 80 && bpdys <= 89)) {
            return 7;
        }

        else{
            return 5;
        }
    }
}