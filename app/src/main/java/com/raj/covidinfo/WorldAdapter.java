package com.raj.covidinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorldAdapter extends RecyclerView.Adapter<WorldAdapter.ViewHolder> {
    private ArrayList<WorldModel> arrayList;
    private Context context;

    public WorldAdapter(ArrayList<WorldModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WorldAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.world_rv_item,parent,false);
        return new WorldAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull WorldAdapter.ViewHolder holder, int position) {

        WorldModel worldModel = arrayList.get(position);
        holder.countryTV.setText(worldModel.getCountryName());
        Picasso.get()
                .load(worldModel.getImageUrl())
                .into(holder.flagIV);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("flagUrl",worldModel.getImageUrl());
                intent.putExtra("countryName",worldModel.getCountryName());
                intent.putExtra("cases",worldModel.getCases());
                intent.putExtra("todayCases",worldModel.getTodayCases());
                intent.putExtra("deaths",worldModel.getDeaths());
                intent.putExtra("todayDeaths",worldModel.getTodayDeaths());
                intent.putExtra("recovered",worldModel.getRecovered());
                intent.putExtra("todayRecovered",worldModel.getTodayRecovered());
                intent.putExtra("active",worldModel.getActive());
                intent.putExtra("critical",worldModel.getCritical());
                intent.putExtra("population",worldModel.getPopulation());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flagIV;
        TextView countryTV;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagIV = itemView.findViewById(R.id.flagIV);
            countryTV = itemView.findViewById(R.id.countryTV);
            cardView = itemView.findViewById(R.id.world_cardView);
        }
    }
}

