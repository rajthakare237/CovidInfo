package com.raj.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GraphActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        navigationView = findViewById(R.id.bottom_navigation);

        navigationView.setSelectedItemId(R.id.graph);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.world:

                        startActivity(new Intent(getApplicationContext(),WorldActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.india:
                        startActivity(new Intent(getApplicationContext(),IndiaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.graph:
                        return true;

                }
                return false;
            }
        });
    }
}