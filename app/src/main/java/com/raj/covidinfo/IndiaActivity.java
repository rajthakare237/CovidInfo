package com.raj.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IndiaActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    ArrayList<String> stateArraylist;
    IndiaAdapter indiaAdapter;
    RecyclerView indiaRV;


    public class DownloadTaskIndia extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... urls) {
            final String result = "";
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(IndiaActivity.this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urls[0], null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray jsonArray = response.getJSONArray("states");

                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String stateName = jsonObject.getString("state");
                                    stateArraylist.add(stateName);
                                    indiaAdapter.notifyDataSetChanged();

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
        setContentView(R.layout.activity_india);
        stateArraylist = new ArrayList<>();
        indiaRV = findViewById(R.id.indiaRV);
        indiaAdapter = new IndiaAdapter(stateArraylist,this);

        indiaRV.setAdapter(indiaAdapter);


        try {
            IndiaActivity.DownloadTaskIndia task = new IndiaActivity.DownloadTaskIndia();
            task.execute("https://disease.sh/v3/covid-19/gov/india");
        } catch (Exception e) {
            e.printStackTrace();
        }

        navigationView = findViewById(R.id.bottom_navigation);

        navigationView.setSelectedItemId(R.id.india);

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