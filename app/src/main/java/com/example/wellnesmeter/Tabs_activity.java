package com.example.wellnesmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.wellnesmeter.tutorial.tutorial_starting;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Tabs_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;


    ConstraintLayout cardiology;
    ConstraintLayout exercise;
    ConstraintLayout dentistry;
    ConstraintLayout psychology;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintab);

        mAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open,  R.string.menu_Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Switch and case statements to be able to map buttons in navigation drawer to their respective actions
                switch (item.getItemId()){
                    case R.id.nav_help:
                        Log.i("MENU_DRAWER_TAG", "Help item has been clicked!");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_share:
                        Log.i("MENU_DRAWER_TAG", "Share item has been clicked!");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logout:
                        Log.i("MENU_DRAWER_TAG", "Log out item has been clicked!");
                        mAuth.signOut();
                        Intent intent = new Intent(Tabs_activity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_settings:
                        Log.i("MENU_DRAWER_TAG", "Settings item has been clicked!");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_users:
                    Log.i("MENU_DRAWER_TAG", "Users item has been clicked!");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                }


                return true;
            }
        });



        ImageView hic = findViewById(R.id.score);
        ImageView health_sensing = findViewById(R.id.sensing);
        ImageView history = findViewById(R.id.history);
        ImageView experts = findViewById(R.id.experts);
        mAuth = FirebaseAuth.getInstance();

        hic.setOnClickListener(view -> startActivity(new Intent(Tabs_activity.this , InputActivity.class)));
        health_sensing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tabs_activity.this, tutorial_starting.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tabs_activity.this, health_history.class));
            }
        });


        // Let's setup the Card View Click activities
        cardiology = findViewById(R.id.cardiology);
        dentistry = findViewById(R.id.dentistry);
        psychology = findViewById(R.id.psychology);
        exercise = findViewById(R.id.exercise);

        cardiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://my.clevelandclinic.org/health/diagnostics/17085-heart-risk-factor-calculators");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        dentistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.who.int/news-room/fact-sheets/detail/oral-health");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        psychology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.manastha.com/online-psychological-tests/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.muscleandstrength.com/workout-routines");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        experts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.practo.com/doctors");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
     @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(Tabs_activity.this, MainActivity.class));
            finish();
        }
    }
}