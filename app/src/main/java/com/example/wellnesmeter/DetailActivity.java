package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry,tvCases,tvCritical,tvDeaths,tvTodayDeaths,tvRecovered,tvActive,tvTodayCases,tvPopulation,tvContinent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        tvCountry = findViewById(R.id.tvCountryName);
        tvCases = findViewById(R.id.tvCases);


    }
}