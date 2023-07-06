package com.example.wellnesmeter;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link temperature#newInstance} factory method to
 * create an instance of this fragment.
 */
public class temperature extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Random r;

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    public temperature() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment temperature.
     */
    // TODO: Rename and change types and number of parameters
    public static temperature newInstance(String param1, String param2) {
        temperature fragment = new temperature();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        r = new Random();
        readHealthData();
    }

    private List<HealthSample> healthSampleList = new ArrayList<>();
    private void readHealthData() {
        String data = "user";
        int output = r.nextInt((3 - 1) + 1) + 1;
        data = data.concat(String.valueOf(output));
        InputStream is;
        if (data.equals("user1")) {
            is = getResources().openRawResource(R.raw.user1);
        } else if (data.equals("user2")) {
            is = getResources().openRawResource(R.raw.user2);
        } else {
            is = getResources().openRawResource(R.raw.user3);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, Charset.forName("UTF-8"))
        );
        // Using Buffered Reader to get the single line of text from the .csv file
        String line = "";
        try {
            // step over headers
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Split by ','
                String[] tokens = line.split(",");

                //Reading the data
                HealthSample sample = new HealthSample();
                sample.setDay(tokens[0]);
                sample.setTemp(Integer.parseInt(tokens[1]));
                sample.setPulse(Integer.parseInt(tokens[2]));
                sample.setDiast(Integer.parseInt(tokens[3]));
                sample.setSyst(Integer.parseInt(tokens[4]));

                healthSampleList.add(sample);

            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
        }
    }

    private void getBarEntries() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        GraphView graph = (GraphView)view.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, healthSampleList.get(0).getTemp()),
                new DataPoint(1, healthSampleList.get(1).getTemp()),
                new DataPoint(2, healthSampleList.get(2).getTemp()),
            //    new DataPoint(3, healthSampleList.get(4).getTemp()),
                new DataPoint(4, healthSampleList.get(3).getTemp())
        });
        graph.addSeries(series);


        barChart = (BarChart)view.findViewById(R.id.BarChart);

        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, healthSampleList.get(0).getTemp()));
        barEntriesArrayList.add(new BarEntry(2f, healthSampleList.get(1).getTemp()));
        barEntriesArrayList.add(new BarEntry(3f, healthSampleList.get(2).getTemp()));
        barEntriesArrayList.add(new BarEntry(4f, healthSampleList.get(3).getTemp()));


        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Body Temperature");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);
        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        return view;
    }
}