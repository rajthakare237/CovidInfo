package com.raj.covidinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StateDetailActivity extends AppCompatActivity {

    ImageButton backButton;

    TextView stateNameHead,casesSt,todayCasesSt,deathsSt,todayDeathsSt,recoveredSt,todayRecoveredSt,activeSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_detail);
        backButton = findViewById(R.id.backBtnState);
        stateNameHead = findViewById(R.id.stateNameHeadTVSt);
        casesSt = findViewById(R.id.casesTVState);
        todayCasesSt = findViewById(R.id.todayCasesTVState);
        deathsSt = findViewById(R.id.deathsTVState);
        todayDeathsSt = findViewById(R.id.todayDeathsTVState);
        recoveredSt = findViewById(R.id.recoveredTVState);
        todayRecoveredSt = findViewById(R.id.todayRecoveredTVState);
        activeSt = findViewById(R.id.activeTVState);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateDetailActivity.this,IndiaActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();

        String stateName = i.getStringExtra("stateName");
        stateNameHead.setText(stateName);

        String cases = i.getStringExtra("cases");
        casesSt.setText(cases);

        String todayCases = i.getStringExtra("todayCases");
        todayCasesSt.setText(todayCases);

        String deaths = i.getStringExtra("deaths");
        deathsSt.setText(deaths);

        String todayDeaths = i.getStringExtra("todayDeaths");
        todayDeathsSt.setText(todayDeaths);

        String recovered = i.getStringExtra("recovered");
        recoveredSt.setText(recovered);

        String todayRecovered = i.getStringExtra("todayRecovered");
        todayRecoveredSt.setText(todayRecovered);

        String active = i.getStringExtra("active");
        activeSt.setText(active);





    }
}