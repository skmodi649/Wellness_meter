package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private TextView bmires;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        weight = (EditText) findViewById(R.id.wt);
        height = (EditText) findViewById(R.id.ht);
        bmires = (TextView) findViewById(R.id.bmires);

        double wt = Double.parseDouble(String.valueOf(weight.getText()));
        double ht = Double.parseDouble(String.valueOf(height.getText()));

        double bmi = wt / (ht*ht);



    }
}