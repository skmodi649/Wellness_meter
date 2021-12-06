package com.example.wellnesmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.leo.simplearcloader.SimpleArcLoader;

public class AffectedCountries extends AppCompatActivity {

    EditText editSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        editSearch = findViewById(R.id.editSearch);
        listView = findViewById(R.id.listView);
        simpleArcLoader = findViewById(R.id.loaderla);


    }
}