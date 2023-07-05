package com.example.wellnesmeter;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class InputActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private TextView Message;
    private ImageView imageView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        weight = findViewById(R.id.wt);
        height = findViewById(R.id.ht);
        Button result = findViewById(R.id.calres);
        imageView = findViewById(R.id.imageView);
        Message = findViewById(R.id.message);


        Glide.with(this).load(R.raw.bmiae).into(imageView);







        result.setOnClickListener(view -> {
            double wt = Double.parseDouble(String.valueOf(weight.getText()));
            double ht = Double.parseDouble(String.valueOf(height.getText()));

            double bmi = wt / (ht*ht);
            bmi = Math.round(bmi*100.0)/100.0;    // Rounding off the bmi value to two decimal places
            String res = Double.toString(bmi);


            if(bmi < 18.50){
                Message.setText("Underweight!");}
            else if(bmi >= 18.50 && bmi <= 24.9){
                Message.setText("Normal!");}
               else if(bmi >= 25 && bmi <= 29.9) {
                    Message.setText("Overweight!");}
                    else {
                        Message.setText("Obese!");
                    }
                });
    }
}