package com.example.wellnesmeter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        Button button = findViewById(R.id.agree);
        Button button1 = findViewById(R.id.decline);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);

    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.agree:
                startActivity(new Intent(TermsAndConditions.this , Tabs_activity.class));
                break;

            case R.id.decline:
                startActivity(new Intent(TermsAndConditions.this , MainActivity.class));
                break;
        }
    }
}
