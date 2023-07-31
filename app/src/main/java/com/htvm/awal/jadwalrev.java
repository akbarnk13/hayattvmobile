package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class jadwalrev extends AppCompatActivity {

    TextView tvj;
    ImageView cv;

    int y;
    int m;
    int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwalrev);
        cv=findViewById(R.id.cv);
        tvj=findViewById(R.id.tvj);
        Calendar c=Calendar.getInstance();
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y=c.get(Calendar.YEAR);
                m=c.get(Calendar.MONTH);
                d=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog g=new DatePickerDialog(jadwalrev.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tvj.setText(i+"/"+i1+"/"+i2);
                    }
                },y,m,d);
                g.show();
            }
        });

    }
}