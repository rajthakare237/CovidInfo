package com.raj.covidinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IndiaAdapter extends RecyclerView.Adapter<IndiaAdapter.ViewHolder> {

    private ArrayList<IndiaModel> stateArraylist;
    private Context context;

    public IndiaAdapter(ArrayList<IndiaModel> stateArraylist, Context context) {
        this.stateArraylist = stateArraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public IndiaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.india_rv_item,parent,false);
        return new IndiaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndiaAdapter.ViewHolder holder, int position) {

        IndiaModel indiaModel = stateArraylist.get(position);

        holder.stateNameTV.setText(indiaModel.getStateName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StateDetailActivity.class);
                intent.putExtra("stateName",indiaModel.getStateName());
                intent.putExtra("cases",indiaModel.getCases());
                intent.putExtra("todayCases",indiaModel.getTodayCases());
                intent.putExtra("deaths",indiaModel.getDeaths());
                intent.putExtra("todayDeaths",indiaModel.getTodayDeaths());
                intent.putExtra("recovered",indiaModel.getRecovered());
                intent.putExtra("todayRecovered",indiaModel.getTodayRecovered());
                intent.putExtra("active",indiaModel.getActive());
                context.startActivity(intent);
            }
        });

    }

    public void filterList(ArrayList<IndiaModel> filteredList){
        stateArraylist = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stateArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stateNameTV;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stateNameTV = itemView.findViewById(R.id.stateName);
            cardView = itemView.findViewById(R.id.indiaCardView);
        }
    }
}
