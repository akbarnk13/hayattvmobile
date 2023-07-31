package com.htvm.awal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.htvm.awal.info.AdapterInfo;
import com.htvm.awal.info.Clubmodel;

import java.util.ArrayList;
public class notifikasi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        TextView textView = findViewById(R.id.textlinkprivacy);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}