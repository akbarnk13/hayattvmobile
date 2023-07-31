package com.htvm.awal.info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htvm.awal.R;

import java.util.ArrayList;

public class AdapterInfo extends RecyclerView.Adapter<AdapterInfo.ViewHolder> {

    private ArrayList<Clubmodel> clubmodel;

    public AdapterInfo(ArrayList<Clubmodel> clubmodel) {
        this.clubmodel = clubmodel;
    }

    @NonNull
    @Override
    public AdapterInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_info,parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInfo.ViewHolder holder, int position) {
        holder.namainfo.setText(clubmodel.get(position).getNamainfo());
        holder.gambarinfo.setImageResource(clubmodel.get(position).getIconInfo());
    }

    @Override
    public int getItemCount() {
        return clubmodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namainfo;
        ImageView gambarinfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namainfo = itemView.findViewById(R.id.namainfo);
            gambarinfo = itemView.findViewById(R.id.logoitem);
        }
    }
}
