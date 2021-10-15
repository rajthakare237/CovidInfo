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



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flagIV;
        TextView countryTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagIV = itemView.findViewById(R.id.flagIV);
            countryTV = itemView.findViewById(R.id.countryTV);
        }
    }
}

