package com.htvm.awal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class lvadapterminggu extends BaseAdapter {

    private ArrayList<String> id;
    private ArrayList<String> jam;
    private ArrayList<String> nama;
    private ArrayList<String> kategori;
    private AppCompatActivity activity;


    public lvadapterminggu(ArrayList<String> id, ArrayList<String> jam, ArrayList<String> nama, ArrayList<String> kategori, AppCompatActivity activity) {
        this.id = id;
        this.jam = jam;
        this.nama = nama;
        this.kategori = kategori;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return id.size();

    }

    @Override
    public Object getItem(int position) {
        return id.get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layoutlist_jming, parent, false);

        convertView.findViewById(R.id.namaprogramacara).setTag(String.valueOf(id.get(position)));

        ((TextView)convertView.findViewById(R.id.namaprogramacara)).setText(String.valueOf(nama.get(position)));

        ((TextView)convertView.findViewById(R.id.tjam)).setText(String.valueOf(jam.get(position)));

        ((TextView)convertView.findViewById(R.id.kategori)).setText(String.valueOf(kategori.get(position)));

        return convertView;
    }
}
