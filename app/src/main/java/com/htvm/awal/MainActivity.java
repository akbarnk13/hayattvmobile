package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    ProgressDialog pd;
    //auto slide
   private Handler slideHandler = new Handler();
   private Button btnkritik, btnfullscreen;
   private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
   private DatabaseReference reference=firebaseDatabase.getReference();
   private DatabaseReference childrefrence=reference.child("url");
   String url;
   TextView txtMarquee;
   private StyledPlayerView playerView;
   private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnkritik = findViewById(R.id.buttonkritik);
        btnkritik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });

        btnfullscreen = findViewById(R.id.buttonfullscreen);

        btnfullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, fullscreenplayer.class);
                startActivity(intent);
                player.stop();
            }
        });

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        playerView = findViewById(R.id.live1);

        //webView=findViewById(R.id.playerlive1);
       // webView.getSettings().setJavaScriptEnabled(true);
        //webView.setWebViewClient(new WebViewClient());
        pd=new ProgressDialog(MainActivity.this);
        pd.setMessage("Menunggu Jaringan..");
        pd.show();

    }

    public void inisiasi(){

        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        childrefrence.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message=snapshot.getValue(String.class);
                MediaItem mediaItem = MediaItem.fromUri(message);
                player.setMediaItem(mediaItem);
                player.prepare();
                player.play();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void showDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Kritik & Saran");
        dialog.setMessage("Masukan Kritik Dan Saran Anda Untuk Pengembangan Aplikasi Ini");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.kirim_kritik, null);
        final MaterialEditText edtemail = reg_layout.findViewById(R.id.edtemail);
        final MaterialEditText edtnama = reg_layout.findViewById(R.id.edtnama);
        final MaterialEditText edtisi = reg_layout.findViewById(R.id.edtisi);

        dialog.setView(reg_layout);

        //setvutton
        dialog.setPositiveButton("Kirim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //cek validasi
                if (TextUtils.isEmpty(edtemail.getText().toString())){
                    Toast.makeText(MainActivity.this, "Tolong Masukan Email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtnama.getText().toString())){
                    Toast.makeText(MainActivity.this, "Tolong Masukan Nama...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtisi.getText().toString())){
                    Toast.makeText(MainActivity.this, "Tolong diisi...", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference();

                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Object value = dataSnapshot.getValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, "Gagal Mengirim...", Toast.LENGTH_SHORT).show();
                    }
                });

                myref.child("Users").child(edtnama.getText().toString()).child("Email").setValue(edtemail.getText().toString());
                myref.child("Users").child(edtnama.getText().toString()).child("Kritik").setValue(edtisi.getText().toString());
                myref.child("Users").child(edtnama.getText().toString()).child("Nama").setValue(edtnama.getText().toString());
                myref.child("Users").child(edtnama.getText().toString()).child("Tgl").setValue(ServerValue.TIMESTAMP);

                Toast.makeText(MainActivity.this, "Terimakasih Atas Masukan nya...",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (player == null) {
            inisiasi();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player.isPlaying()){
            player.stop();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        inisiasi();
        childrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message=snapshot.getValue(String.class);
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });

    }

    public void kontak(View view){
        Intent intent = new Intent( MainActivity.this, contact.class);
        startActivity(intent);
        player.stop();
    }

    public void artikell(View view){
        Intent intent = new Intent( MainActivity.this, artikel.class);
        startActivity(intent);
        player.stop();
    }

    //public void pertanyaann(View view){
    //    Intent intent = new Intent( MainActivity.this, pertanyaan.class);
     //   startActivity(intent);
    //}



    public void jdwl(View view){
        Intent intent = new Intent(MainActivity.this, jadwal.class);
        startActivity(intent);
        player.stop();
    }

    public void livetv(View view){
        Intent intent = new Intent(MainActivity.this, livechat.class);
        startActivity(intent);
        player.stop();
    }

    public void jdwlrv(View view){
        Intent intent = new Intent(MainActivity.this, jadwalrev.class);
        startActivity(intent);
    }

    public void info(View view){
        Intent intent = new Intent( MainActivity.this, informasi.class);
        startActivity(intent);
        player.stop();
    }

    public void fullscreenplayerr(View view){
        Intent intent = new Intent( MainActivity.this, fullscreenplayer.class);
        startActivity(intent);
    }

    public void notip(View view){
        Intent intent = new Intent( MainActivity.this, notifikasi.class);
        startActivity(intent);
        player.stop();
    }

}