package com.raj.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;


    TextView casesTV, todayCasesTV, deathsTV, todayDeathsTV, recoveredTV, todayRecoveredTV, activeTV, criticalTV;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            final String result = "";
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(MainActivity.this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urls[0], null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String cases = response.getString("cases");
                                String todayCases = response.getString("todayCases");
                                String deaths = response.getString("deaths");
                                String todayDeaths = response.getString("todayDeaths");
                                String recovered = response.getString("recovered");
                                String todayRecovered = response.getString("todayRecovered");
                                String active = response.getString("active");
                                String critical = response.getString("critical");




                                casesTV.setText(cases);
                                todayCasesTV.setText(todayCases);
                                deathsTV.setText(deaths);
                                todayDeathsTV.setText(todayDeaths);
                                recoveredTV.setText(recovered);
                                todayRecoveredTV.setText(todayRecovered);
                                activeTV.setText(active);
                                criticalTV.setText(critical);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("raj", "onErrorResponse: Something went wrong ");
                }
            });

            requestQueue.add(jsonObjectRequest);
            return null;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casesTV = findViewById(R.id.casesTV);
        todayCasesTV = findViewById(R.id.todayCasesTV);
        deathsTV = findViewById(R.id.deathsTV);
        todayDeathsTV = findViewById(R.id.todayDeathsTV);
        recoveredTV = findViewById(R.id.recoveredTV);
        todayRecoveredTV = findViewById(R.id.todayRecoveredTV);
        activeTV = findViewById(R.id.activeTV);
        criticalTV = findViewById(R.id.criticalTV);

        navigationView = findViewById(R.id.bottom_navigation);


        try {
            DownloadTask task = new DownloadTask();
            task.execute("https://disease.sh/v3/covid-19/all");
        } catch (Exception e) {
            e.printStackTrace();
        }

        navigationView.setSelectedItemId(R.id.home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;


                switch (item.getItemId()) {

                    case R.id.home:
                        return true;

                    case R.id.world:
                        startActivity(new Intent(getApplicationContext(), WorldActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.india:
                        startActivity(new Intent(getApplicationContext(), IndiaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}