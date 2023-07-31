package com.htvm.awal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class artikel extends AppCompatActivity {

    private TextView urlartikel;
    private WebView webView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pd;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();
    private DatabaseReference childreference=reference.child("artikelurl");
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pd=new ProgressDialog(artikel.this);
        pd.setMessage("Menunggu Jaringan..");
        pd.show();

        mProgressbar=findViewById(R.id.progressbar);

        setContentView(R.layout.activity_artikel);
        urlartikel=findViewById(R.id.artikelurl);
        webView=findViewById(R.id.webvartikel);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mProgressbar !=null) {
                    mProgressbar.setVisibility(View.GONE);
                }
            }
        });

        registerForContextMenu(webView);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        childreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message=snapshot.getValue(String.class);
                urlartikel.setText(message);
                webView.loadUrl(message);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pd.dismiss();
                    }
                }, 5000); // 5 seconds delay
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
