package com.htvm.awal.hari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.htvm.awal.Jadwal_minggu;
import com.htvm.awal.R;
import com.htvm.awal.lvadapterminggu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class j_rabu extends AppCompatActivity {
    public DatabaseReference Dbkoneksi;
    public ListView listviewjadwalminggu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jrabu);

        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Dbkoneksi = FirebaseDatabase.getInstance().getReference().child("Rabu");
        listviewjadwalminggu = findViewById(R.id.lvminggu);

        readData();
    }

    private void readData() {
        final ArrayList<String> id = new ArrayList<>();
        final ArrayList<String> jam = new ArrayList<>();
        final ArrayList<String> nama = new ArrayList<>();
        final ArrayList<String> kategori = new ArrayList<>();

        Dbkoneksi.orderByChild("jam").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();

                while (iterator.hasNext()) {

                    Jadwal_minggu value = iterator.next().getValue(Jadwal_minggu.class);

                    assert value !=null;

                    id.add(value.getId());
                    jam.add(value.getJam());
                    nama.add(value.getNama());
                    kategori.add(value.getKategori());

                    ((lvadapterminggu) listviewjadwalminggu.getAdapter()).notifyDataSetChanged();
                }
            } @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        listviewjadwalminggu.setAdapter(new lvadapterminggu(id, jam, nama, kategori, this));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}