package com.htvm.awal.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htvm.awal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {
    List<Map<String, String>> datas = new ArrayList<>();

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ScheduleHolder(inflater.inflate(R.layout.layoutlist_jming, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        holder.bind(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    protected static class ScheduleHolder extends RecyclerView.ViewHolder {
        private final TextView hour, name, category, stats;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            hour = itemView.findViewById(R.id.tjam);
            name = itemView.findViewById(R.id.namaprogramacara);
            stats = itemView.findViewById(R.id.status);
            category = itemView.findViewById(R.id.kategori);
        }

        void bind(Map<String, String> data) {
            hour.setText(data.get("jam"));
            name.setText(data.get("nama"));
            stats.setText(data.get("status"));
            category.setText(data.get("kategori"));
        }
    }

    void submitList(List<Map<String, String>> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
}
