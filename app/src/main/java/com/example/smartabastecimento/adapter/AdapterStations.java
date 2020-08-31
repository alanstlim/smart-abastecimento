package com.example.smartabastecimento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartabastecimento.R;
import com.example.smartabastecimento.model.Station;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterStations extends RecyclerView.Adapter<AdapterStations.MyViewHolder>{

    private List<Station> listStations;
    private Context context;
    private StorageReference storageReference;

    public AdapterStations(List<Station> list, Context c) {
        this.listStations = list;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemStations = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapterstations_list, parent, false);
        return new MyViewHolder(itemStations);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Station stations = listStations.get(position);
        storageReference = FirebaseStorage.getInstance().getReference().child("franchise/" + stations.getImageFranchise() + "");
        holder.textNameStation.setText(stations.getNameStation());
        holder.textPriceEthanol.setText("R$: " + stations.getPriceEthanol());
        holder.textPriceGasoline.setText("R$: " + stations.getPriceGasoline());
        holder.textDistance.setText(stations.getAddress() + " " + stations.getNumber());
        Glide.with(context)
                .load(storageReference)
                .into(holder.imageFranchise);

    }
    @Override
    public int getItemCount() {
        return listStations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageFranchise;
        TextView textNameStation, textDistance, textPriceEthanol, textPriceGasoline;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFranchise = itemView.findViewById(R.id.imageFranchise);
            textNameStation = itemView.findViewById(R.id.textNameStation);
            textDistance = itemView.findViewById(R.id.textDistance);
            textPriceEthanol = itemView.findViewById(R.id.textPriceEthanol);
            textPriceGasoline = itemView.findViewById(R.id.textPriceGasoline);
        }
    }
}
