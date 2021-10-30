package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    TextView result_score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result_score = findViewById(R.id.rescore);

        Bundle b = getIntent().getExtras();
        String receivingdata = b.getString("key");
        double data = Double.parseDouble(receivingdata);
        double percent = (data * 100) / 70;
        percent = Math.round(percent * 100.0) / 100.0;
        int val = (int) percent;
        String percentstr = Integer.toString(val).trim();
        percentstr = percentstr + "%";
        result_score.setText(percentstr);
    }
}