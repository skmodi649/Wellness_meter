package com.example.wellnesmeter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener{
    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        button = (Button) findViewById(R.id.agree);
        button1 = (Button) findViewById(R.id.decline);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);

    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.agree:
                startActivity(new Intent(TermsAndConditions.this , InputActivity.class));
                break;

            case R.id.decline:
                startActivity(new Intent(TermsAndConditions.this , MainActivity.class));
                break;
        }
    }

}
