package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;



public class ResultActivity extends AppCompatActivity {

    TextView result_score;
    TextView result_bmi;
    TextView result_pulse;
    TextView result_bp;
    TextView result_sugar;
    TextView result_sodium;
    TextView result_resp;
    TextView result_cholesterol;
    Button home;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result_score = findViewById(R.id.rescore);
        result_bmi = findViewById(R.id.disbmi);
        result_pulse = findViewById(R.id.dispulse);
        result_bp = findViewById(R.id.disbp);
        result_sugar = findViewById(R.id.disugar);
        result_sodium = findViewById(R.id.disodium);
        result_resp = findViewById(R.id.disresp);
        result_cholesterol = findViewById(R.id.discholes);

        Bundle b = getIntent().getExtras();
        String receivingdata = b.getString("key");
        double data = Double.parseDouble(receivingdata);
        double percent = (data * 100) / 70;
        percent = Math.round(percent * 100.0) / 100.0;
        int val = (int) percent;
        String percentstr = Integer.toString(val).trim();
        percentstr = percentstr + "%";
        result_score.setText(percentstr);

        String bmidata = b.getString("bmi");
        result_bmi.setText(bmidata);
        String pulsedata = b.getString("pulse");
        result_pulse.setText(pulsedata);
        String bpdata = b.getString("bp");
        result_bp.setText(bpdata);
        String sodiumdata = b.getString("sodium");
        result_sodium.setText(sodiumdata);
        String respdata = b.getString("respiratory");
        result_resp.setText(respdata);
        String sugardata = b.getString("sugar");
        result_sugar.setText(sugardata);
        String cholesteroldata = b.getString("cholesterol");
        result_cholesterol.setText(cholesteroldata);

        home = findViewById(R.id.profhome);
        home.setOnClickListener(view -> startActivity(new Intent(ResultActivity.this , InputActivity.class)));
    }
}