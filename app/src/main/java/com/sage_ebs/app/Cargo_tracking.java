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

public class Cargo_tracking extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_tracking);

        webview = findViewById(R.id.webview);
        webview.loadUrl("https://creator.zohopublic.com/sageplatform/ebs/form-embed/Cargo_Tracking/kfWe5THgsByJ72eyQKDKP863aahMJhGpBYDWQdUxKPX5xgtRU5U0xzwYS48JZOg3nY90xGugwTEbTdaJXxhNbn97A5rBd792A9Hs");
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_ALWAYS);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        }else if(id == R.id.cargo_tracking){
            startActivity(new Intent(getApplicationContext(),CargoActivity.class));
            return true;
        }else if (id== R.id.btn_back) {
            startActivity(new Intent(getApplicationContext(), TryThisActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
