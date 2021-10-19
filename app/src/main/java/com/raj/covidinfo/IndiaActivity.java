package com.raj.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
    private ArrayList<IndiaModel> stateArraylist;
    IndiaAdapter indiaAdapter;
    RecyclerView indiaRV;
    EditText editText;


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
                                    String cases = jsonObject.getString("cases");
                                    String todayCases = jsonObject.getString("todayCases");
                                    String deaths = jsonObject.getString("deaths");
                                    String todayDeaths = jsonObject.getString("todayDeaths");
                                    String recovered = jsonObject.getString("recovered");
                                    String todayRecovered = jsonObject.getString("todayRecovered");
                                    String active = jsonObject.getString("active");
                                    stateArraylist.add(new IndiaModel(stateName,cases,todayCases,deaths,todayDeaths,recovered,todayRecovered,active));
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
        editText = findViewById(R.id.searchEditTextIndia);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              filterStates(s.toString());
            }


        });



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

                }
                return false;
            }
        });
    }

    private void filterStates(String state) {
            ArrayList<IndiaModel> filteredList = new ArrayList<>();
            for (IndiaModel item : stateArraylist) {
                if (item.getStateName().toLowerCase().contains(state.toLowerCase())) {
                    filteredList.add(item);
                }
                if(!filteredList.isEmpty()){
                    indiaAdapter.filterList(filteredList);
                }



            }
                if (filteredList.isEmpty()) {
                    Toast.makeText(this, "No State Found ", Toast.LENGTH_SHORT).show();

                }
    }
}