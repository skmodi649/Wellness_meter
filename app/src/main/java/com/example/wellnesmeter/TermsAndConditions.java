package com.example.wellnesmeter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener{
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        button = (Button) findViewById(R.id.agree);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View view)
    {
        startActivity(new Intent(TermsAndConditions.this , ProfileActivity.class));
    }
}
