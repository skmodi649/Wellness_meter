package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private TextView bmires;
    private Button result;
    private Button logout;
    private TextView Message;
    private Button Health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        weight = (EditText) findViewById(R.id.wt);
        height = (EditText) findViewById(R.id.ht);
        bmires = (TextView) findViewById(R.id.bmires);
        result = (Button) findViewById(R.id.calres);
        logout = (Button) findViewById(R.id.lout);
        Message = (TextView) findViewById(R.id.message);
        Health = (Button) findViewById(R.id.health);

        result.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                double wt = Double.parseDouble(String.valueOf(weight.getText()));
                double ht = Double.parseDouble(String.valueOf(height.getText()));

                double bmi = wt / (ht*ht);
                bmi = Math.round(bmi*100.0)/100.0;    // Rounding off the bmi value to two decimal places
                String res = Double.toString(bmi);

                bmires.setText(res);

                if(bmi < 18.50){
                    Message.setText("Underweight!");}
                else if(bmi >= 18.50 && bmi <= 24.9){
                    Message.setText("Normal!");}
                   else if(bmi >= 25 && bmi <= 29.9) {
                        Message.setText("Overweight!");}
                        else {
                            Message.setText("Obese!");
                        }
                    }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this , MainActivity.class));
            }
        });

        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this , HealthIndexCalc.class));
            }
        });
    }
}