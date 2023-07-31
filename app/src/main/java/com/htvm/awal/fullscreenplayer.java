package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fullscreenplayer extends AppCompatActivity {
    StyledPlayerView playerView;
    ExoPlayer player1;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();
    private DatabaseReference childrefrence=reference.child("url");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreenplayer);

        playerView = findViewById(R.id.livefullscreen);

    }


    public void inisiasi(){

        player1 = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player1);
        childrefrence.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message=snapshot.getValue(String.class);
                MediaItem mediaItem = MediaItem.fromUri(message);

                player1.setMediaItem(mediaItem);
                player1.prepare();
                player1.play();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        player1.stop();
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player1.isPlaying()){
            player1.stop();
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        inisiasi();
    }
}