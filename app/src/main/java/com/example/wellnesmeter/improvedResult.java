package com.example.wellnesmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class improvedResult extends AppCompatActivity {

    TextView tvbp, tvpulse, tvcholesterol, tvsodium, tvcalcium, tvrespiratory, tvbmi, tvsugar;
    PieChart pieChart;
    TextView finalScore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_result);

        mAuth = FirebaseAuth.getInstance();


        tvbp = findViewById(R.id.tvbp);
        tvpulse = findViewById(R.id.tvpulse);
        tvcholesterol = findViewById(R.id.tvcholesterol);
        tvsodium = findViewById(R.id.tvsodium);
        tvcalcium = findViewById(R.id.tvsodium);
        tvrespiratory = findViewById(R.id.tvrespiratory);
        tvbmi = findViewById(R.id.tvbmi);
        tvsugar = findViewById(R.id.tvsugar);

        Bundle b = getIntent().getExtras();
        String receivingdata = b.getString("key");
        double data = Double.parseDouble(receivingdata);
        double percent = (data * 100) / 80;
        percent = Math.round(percent * 100.0) / 100.0;
        int val = (int) percent;
        int left = 100 - val;
        String percentstr = Integer.toString(val).trim();
        percentstr = percentstr + "%";

        finalScore = findViewById(R.id.finalScore);

        String bmidata = b.getString("bmi");
        tvbmi.setText(bmidata);
        String pulsedata = b.getString("pulse");
        tvpulse.setText(pulsedata);
        String bpdata = b.getString("bp");
        tvbp.setText(bpdata);
        String sodiumdata = b.getString("sodium");
        tvsodium.setText(sodiumdata);
        String calciumdata = b.getString("calcium");
        tvcalcium.setText(calciumdata);
        String respdata = b.getString("respiratory");
        tvrespiratory.setText(respdata);
        String sugardata = b.getString("sugar");
        tvsugar.setText(sugardata);
        String cholesteroldata = b.getString("cholesterol");
        tvcholesterol.setText(cholesteroldata);

        //Now lets set data for the pie Chart
        pieChart = findViewById(R.id.piechart);
        finalScore.setText(percentstr);
        pieChart.addPieSlice(new PieModel("Overall Score", val, Color.parseColor("#FFA726")));
        pieChart.addPieSlice(new PieModel("Improvement", left, Color.parseColor("#80CBC4")));
        pieChart.startAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home){
            mAuth.signOut();
            startActivity(new Intent(improvedResult.this, Tabs_activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}