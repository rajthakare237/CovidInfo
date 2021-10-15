package com.raj.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView flagIVDet;

    TextView countryNameHead,casesTVDet, todayCasesTVDet, deathsTVDet, todayDeathsTVDet, recoveredTVDet, todayRecoveredTVDet, activeTVDet, criticalTVDet,populationTVDet;

    TextView deathsTV;

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        backButton = findViewById(R.id.backBtnCountry);
        flagIVDet = findViewById(R.id.flagIVDet);
        countryNameHead = findViewById(R.id.countryNameHeadTV);
        casesTVDet = findViewById(R.id.casesTVDet);
        todayCasesTVDet = findViewById(R.id.todayCasesTVDet);
        deathsTVDet = findViewById(R.id.deathsTVDet);
        todayDeathsTVDet = findViewById(R.id.todayDeathsTVDet);
        recoveredTVDet = findViewById(R.id.recoveredTVDet);
        todayRecoveredTVDet = findViewById(R.id.todayRecoveredTVDet);
        activeTVDet = findViewById(R.id.activeTVDet);
        criticalTVDet = findViewById(R.id.criticalTVDet);
        populationTVDet = findViewById(R.id.populationTVDet);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,WorldActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();

        String flagUrl = i.getStringExtra("flagUrl");
        Picasso.get()
                .load(flagUrl)
                .into(flagIVDet);


        String countryName = i.getStringExtra("countryName");
        countryNameHead.setText(countryName);

        String cases = i.getStringExtra("cases");
        casesTVDet.setText(cases);

        String todayCases = i.getStringExtra("todayCases");
        todayCasesTVDet.setText(todayCases);

        String deaths = i.getStringExtra("deaths");
        deathsTVDet.setText(deaths);

        String todayDeaths = i.getStringExtra("todayDeaths");
        todayDeathsTVDet.setText(todayDeaths);

        String recovered = i.getStringExtra("recovered");
        recoveredTVDet.setText(recovered);

        String todayRecovered = i.getStringExtra("todayRecovered");
        todayRecoveredTVDet.setText(todayRecovered);

        String active = i.getStringExtra("active");
        activeTVDet.setText(active);

        String critical = i.getStringExtra("critical");
        criticalTVDet.setText(critical);

        String population = i.getStringExtra("population");
        populationTVDet.setText(population);




    }
}