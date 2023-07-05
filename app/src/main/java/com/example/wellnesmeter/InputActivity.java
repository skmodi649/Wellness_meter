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

            String value = Double.toString(bmi);
            Bundle bundle = new Bundle();
            bundle.putString("value",value);
            Intent intent = new Intent(InputActivity.this, bmiActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
                });
    }
}