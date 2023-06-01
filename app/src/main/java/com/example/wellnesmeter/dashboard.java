package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class dashboard extends AppCompatActivity {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    ScrollView scrollView;
    Button track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.pieChart);

        track = findViewById(R.id.track);


        //Function to fetch data from the Rest API
        fetchData();

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this , AffectedCountries.class));
            }
        });
    }


    // Now we are going to use the Rest API for the covid 19
    private void fetchData(){

        String url = "https://corona.lmao.ninja/v2/all";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Now we have to handle the JSON text

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvCases.setText(jsonObject.getString( "cases"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));


                    //Now lets set data for the pie Chart

                    pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(dashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }

    public void goTrackCountries(View view) {

    }
}