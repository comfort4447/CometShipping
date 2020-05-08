package com.sage_ebs.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.auth.FirebaseAuth;

public class DepartureActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView webview;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departure);

        webview = findViewById(R.id.webview);
        webview.loadUrl("https://creator.zohopublic.com/sageplatform/ebs/report-embed/mobile_view_vessel_arr_departure_embed/ZEsfrgExump0sr1h6feCNhtTGGskV08FM5atupmbZVk0anXXrGsMUKzDTshtDAsXJ3SbQVwnuM9jCe2F3ayTPs48FOmxhVVJYjTR");
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_ALWAYS);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.arrival) {
            startActivity(new Intent(getApplicationContext(), DepartureActivity.class));
            return true;
        } else if (id == R.id.cargo_tracking) {
            startActivity(new Intent(getApplicationContext(), Cargo_tracking.class));
            return true;
        } else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,MainActivity.class));
            return true;
        } else if (id == R.id.btn_back) {
            startActivity(new Intent(getApplicationContext(), TryThisActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

