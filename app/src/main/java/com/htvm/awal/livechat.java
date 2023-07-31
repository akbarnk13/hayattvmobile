package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class livechat extends AppCompatActivity {
    Button sendbutton;
    EditText pesan, pengirim;
    ListView listView;
   ArrayAdapter msgadapter;
   DatabaseReference myRef;

    StyledPlayerView playerView;
    ExoPlayer player1;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();
    private DatabaseReference childrefrence=reference.child("url");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_livechat);


        myRef = database.getReference("Livechat");
        playerView = findViewById(R.id.livechatvideo);

        sendbutton=findViewById(R.id.kirim);
        pengirim=findViewById(R.id.sender);
        pesan=findViewById(R.id.pesan);
        listView=findViewById(R.id.listviewchat);

        ArrayList msglist=new ArrayList<String>();
        msgadapter= new ArrayAdapter<String>(this, R.layout.listitem,msglist);

        listView.setAdapter(msgadapter);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sender = pengirim.getText().toString();
                String msg = pesan.getText().toString();

                if (msg.length() > 0 && sender.length() > 0) {
                    // Validate the message and censor the word "babi" with ***
                    String censoredMsg = censorMessage(msg);

                    // msgadapter.add(sender + ">" + censoredMsg);
                    myRef.push().setValue(sender + "  :  " + censoredMsg);

                    pesan.setText("");
                }
            }
        });

        loadmsg();

    }

    private String censorMessage(String message) {
        // Define the words to be censored
        String[] wordsToCensor = {"babi", "bangsat", "kontol", "peler", "anjing", "tahi", "tai"};

        // Replace inappropriate words with ***
        for (String word : wordsToCensor) {
            message = message.replaceAll("(?i)\\b" + word + "\\b", "***");
        }

        return message;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inisiasi();
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
    private void loadmsg(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                msgadapter.add(snapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}