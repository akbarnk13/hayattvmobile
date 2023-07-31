package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.htvm.awal.schedule.ScheduleAdapter;
import com.htvm.awal.schedule.ScheduleFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class jadwal extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TAG", "Tab position: " + tab.getPosition());
                moveFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        getCurrentDayName();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveFragment(int position) {
        List<String> days = new ArrayList<String>() {{
            add("Minggu");
            add("Senin");
            add("Selasa");
            add("Rabu");
            add("Kamis");
            add("Jumat");
            add("Sabtu");
        }};

        Log.d("TAG", "Selected day: " + days.get(position));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_view, ScheduleFragment.newInstance(days.get(position)))
                .commit();
    }

    private void getCurrentDayName() {
        Calendar calendar = Calendar.getInstance();
        int dayInt = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if(dayInt < 7) {
            moveFragment(dayInt);
            tabLayout.setScrollPosition(dayInt, 0f, false);
            tabLayout.getTabAt(dayInt).select();
        }
    }
}