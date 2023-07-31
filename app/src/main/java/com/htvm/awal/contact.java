package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class contact extends AppCompatActivity implements ValueEventListener {

    private static final int REQUEST_CALL = 1;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference cs1 = databaseReference.child("CS1");
    private DatabaseReference cs2 = databaseReference.child("CS2");

    private Button callbutton1,callbutton2,wa1, navigasimaps, emailgmail;
    private TextView notelpp1,notelpp2, cshytwa, cshyttelp, emailaddres, subjectaddres, messageaddres;
    private ImageView imgyt, imgfb, imgwww, imgig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        cs1 = FirebaseDatabase.getInstance().getReference("CS1");
        cs2 = FirebaseDatabase.getInstance().getReference("CS2");

        cshytwa = findViewById(R.id.cshayatwa);
        cshyttelp = findViewById(R.id.cshayattelp);

        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notelpp1 = findViewById(R.id.notelp1);
        callbutton1 = findViewById(R.id.telp1);

        emailaddres = findViewById(R.id.emailaddres);
        subjectaddres = findViewById(R.id.subjectemail);
        messageaddres = findViewById(R.id.emailmessage);

        imgyt = findViewById(R.id.imageyt);
        imgfb = findViewById(R.id.imagefacebook);
        imgwww = findViewById(R.id.imagewebsite);
        imgig = findViewById(R.id.imageig);

        navigasimaps = findViewById(R.id.navigasitombol);
        emailgmail = findViewById(R.id.email);

        wa1 = findViewById(R.id.wa1);
        wa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cssatu= cshytwa.getText().toString();
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(cssatu));
                startActivity(intent);
            }
        });

        emailgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String recepient = emailaddres.getText().toString();
            String subject = subjectaddres.getText().toString();
            String messages = messageaddres.getText().toString();

            sendemail(recepient, subject, messages);
            }

            private void sendemail(String recepient, String subject, String messages) {
                Intent mEmailIntent = new Intent(Intent.ACTION_SEND);

                mEmailIntent.setData(Uri.parse("mailto:"));
                mEmailIntent.setType("text/plain");

                mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recepient});
                mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                mEmailIntent.putExtra(Intent.EXTRA_TEXT, messages);

                try {
                        startActivity(Intent.createChooser(mEmailIntent, "Choose an Email Client"));
                }
                catch (Exception e){

                }
            }

        });

        imgig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://instagram.com/hayattvsampit?igshid=MzRlODBiNWFlZA=="));
                startActivity(intent);
            }
        });

        imgfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/jurnalkotimhayattv?mibextid=LQQJ4d"));
                startActivity(intent);
            }
        });

        imgwww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://hayattvsampit.com/home"));
                startActivity(intent);
            }
        });

        imgyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://youtube.com/@HayatTVProduction"));
                startActivity(intent);
            }
        });

        navigasimaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://maps.app.goo.gl/rjqTRpQj2zE6HizD6?g_st=iw"));
                startActivity(intent);
            }
        });

        callbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilcall1();
            }
        });
    }



    private void ambilcall1() {
        String number = cshyttelp.getText().toString();
        if ((number.trim().length() > 0)) {
            if (ContextCompat.checkSelfPermission(contact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(contact.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ambilcall1();

            } else {
                Toast.makeText(this, "Ijin Dibatalkan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
     if (snapshot.getValue(String.class)!=null){
         String key = snapshot.getKey();
         if (key.equals("CS1")){
             String cs1 = snapshot.getValue(String.class);
             cshytwa.setText(cs1);
         }
         if (key.equals("CS2")){
             String cs2 = snapshot.getValue(String.class);
             cshyttelp.setText(cs2);
         }
     }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    protected void onStart(){
        super.onStart();
        cs1.addValueEventListener(this);

    }
}