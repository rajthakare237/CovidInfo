package com.raj.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorldActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    RecyclerView worldRV;
    ArrayList<WorldModel> worldModelArrayList;
    WorldAdapter worldArrayAdapter;
    EditText searchEditText;



    public class DownloadTaskWorld extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... urls) {
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(WorldActivity.this);

            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, urls[0], null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                for(int i=0;i<response.length();i++){
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    String countryName = jsonObject.getString("country");
//                                    String cases = jsonObject.getString("cases");
//                                    String todayCases = jsonObject.getString("todayCases");
//                                    String deaths = jsonObject.getString("deaths");
//                                    String todayDeaths = jsonObject.getString("todayDeaths");
//                                    String recovered = jsonObject.getString("recovered");
//                                    String todayRecovered = jsonObject.getString("todayRecovered");
//                                    String active = jsonObject.getString("active");
//                                    String critical = jsonObject.getString("critical");
//                                    String population = jsonObject.getString("population");

                                    String countryInfo = jsonObject.getString("countryInfo");
                                    JSONObject jsonObject1 = new JSONObject(countryInfo);

                                    String flagUrl = jsonObject1.getString("flag");


                                    worldModelArrayList.add(new WorldModel(flagUrl,countryName));
                                    worldArrayAdapter.notifyDataSetChanged();

//                                    ,cases,todayCases,deaths,todayDeaths,recovered,todayRecovered,active,critical,population

                                }

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
        setContentView(R.layout.activity_world);
        navigationView = findViewById(R.id.bottom_navigation);
        worldModelArrayList = new ArrayList<>();
        worldRV = findViewById(R.id.worldRV);
        worldArrayAdapter = new WorldAdapter(worldModelArrayList,this);
        worldRV.setAdapter(worldArrayAdapter);


        try {
            DownloadTaskWorld task = new DownloadTaskWorld();
            task.execute("https://disease.sh/v3/covid-19/countries");
        } catch (Exception e) {
            e.printStackTrace();
        }

        navigationView.setSelectedItemId(R.id.world);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.world:
                        return true;

                    case R.id.india:

                        startActivity(new Intent(getApplicationContext(),IndiaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.graph:
                        startActivity(new Intent(getApplicationContext(),GraphActivity.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }
}