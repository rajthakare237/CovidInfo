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

    ArrayList<String> stateArraylist;
    private Context context;

    public IndiaAdapter(ArrayList<String> stateArraylist, Context context) {
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

        holder.stateNameTV.setText(stateArraylist.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                context.startActivity(intent);
            }
        });

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
