package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    TextView result_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result_score = findViewById(R.id.rescore);

        Bundle b = this.getIntent().getExtras();
        String i = b.getString("key");

        double total = Double.parseDouble(String.valueOf(i));

        double totpercent = (total * 100) / 70;
        String percent = Double.toString(totpercent);

        result_score.setText(percent);
    }
}