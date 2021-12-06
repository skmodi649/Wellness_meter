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
        tvCritical = findViewById(R.id.tvCritical);
        tvDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvPopulation = findViewById(R.id.tvPopulation);
        tvContinent = findViewById(R.id.tvContinent);

        tvCountry.setText(AffectedCountries.countryModelList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
        tvCritical.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());
        tvActive.setText(AffectedCountries.countryModelList.get(positionCountry).getActiveCases());
        tvTodayCases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
        tvDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayDeaths());
        tvPopulation.setText(AffectedCountries.countryModelList.get(positionCountry).getPopulation());
        tvContinent.setText(AffectedCountries.countryModelList.get(positionCountry).getContinent());


    }
}