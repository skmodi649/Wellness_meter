package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


public class ResultActivity extends AppCompatActivity {

    TextView result_score;
    TextView result_bmi;
    TextView result_pulse;
    TextView result_bp;
    TextView result_sugar;
    TextView result_sodium;
    TextView result_resp;
    TextView result_cholesterol;
    TextView result_calcium;
    Button home;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    ScrollView scrollView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.pieChart);

        result_score = findViewById(R.id.rescore);
        result_bmi = findViewById(R.id.disbmi);
        result_pulse = findViewById(R.id.dispulse);
        result_bp = findViewById(R.id.disbp);
        result_sugar = findViewById(R.id.disugar);
        result_sodium = findViewById(R.id.disodium);
        result_resp = findViewById(R.id.disresp);
        result_cholesterol = findViewById(R.id.discholes);
        result_calcium = findViewById(R.id.dicalcium);

        Bundle b = getIntent().getExtras();
        String receivingdata = b.getString("key");
        double data = Double.parseDouble(receivingdata);
        double percent = (data * 100) / 80;
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
        String calciumdata = b.getString("calcium");
        result_calcium.setText(calciumdata);
        String respdata = b.getString("respiratory");
        result_resp.setText(respdata);
        String sugardata = b.getString("sugar");
        result_sugar.setText(sugardata);
        String cholesteroldata = b.getString("cholesterol");
        result_cholesterol.setText(cholesteroldata);

        pieChart.addPieSlice(new PieModel("Overall Score", Integer.parseInt(percentstr), Color.parseColor("#FFA726")));
        pieChart.startAnimation();

        simpleArcLoader.stop();
        simpleArcLoader.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        home = findViewById(R.id.profhome);
        home.setOnClickListener(view -> startActivity(new Intent(ResultActivity.this , Tabs_activity.class)));
    }
}