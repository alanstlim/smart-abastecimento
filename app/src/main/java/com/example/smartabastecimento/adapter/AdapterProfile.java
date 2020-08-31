package com.example.smartabastecimento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartabastecimento.R;
import com.example.smartabastecimento.model.Profile;

import java.util.List;

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.MyViewHolder>{
    private List<Profile> listProfile;
    private Context context;

    public AdapterProfile(List<Profile> list, Context c) {
        this.listProfile = list;
        this.context = c;
    }

    @NonNull
    @Override
    public AdapterProfile.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemProfile = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapterprofile_list, parent, false);

        return new AdapterProfile.MyViewHolder(itemProfile);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProfile.MyViewHolder holder, int position) {

        Profile menuProfile = listProfile.get(position);
        holder.textNameMenu.setText(menuProfile.getNameMenu());
        holder.textDescriptionMenu.setText(menuProfile.getDescriptionMenu());
        holder.imgIconMenu.setImageResource(menuProfile.getIconMenu());

    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIconMenu;
        TextView textNameMenu, textDescriptionMenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIconMenu             = itemView.findViewById(R.id.imgIconMenu);
            textNameMenu            = itemView.findViewById(R.id.textNameMenu);
            textDescriptionMenu     = itemView.findViewById(R.id.textDescriptionMenu);
        }
    }

}
