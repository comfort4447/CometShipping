package com.sage_ebs.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class TryThisActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
   private DrawerLayout drawerLayout;
   private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_this);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(newListner);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.side_navigation);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
       drawerLayout.addDrawerListener(toggle);
         toggle.setDrawerIndicatorEnabled(true);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
          navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         int id = item .getItemId();
         switch (id){
        case R.id.Profile:
        Toast.makeText(TryThisActivity.this, "welcome", Toast.LENGTH_SHORT).show();
         case R.id.Departure:
        Toast.makeText(TryThisActivity.this, "welcome", Toast.LENGTH_SHORT).show();
         default:
        return true;
        }
         }
        });
         }

       public void onBackPressed(){
          if (drawerLayout.isDrawerOpen(Gravity.LEFT)){
          drawerLayout.closeDrawer(Gravity.RIGHT);
       }else {
       super.onBackPressed();
          }
    }





    private BottomNavigationView.OnNavigationItemSelectedListener
            newListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.services:
                    selectedFragment = new ArrivalFragment();
                    break;
                case R.id.tracking:
                    selectedFragment = new TrackingFragment();
                    break;
                case R.id.VGM:
                    selectedFragment = new RequestFragment();
                    break;
                case R.id.Transfer:
                    selectedFragment = new TransferFragment();
                    break;
                case R.id.logout:
                    selectedFragment = new LogoutFragment();
                    break;
                default:
                    return false;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
            return true;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (item!= null && item.getItemId() == R.id.Departure){
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
            return true;
        }
        if (id == R.id.arrival) {
            startActivity(new Intent(getApplicationContext(), DepartureActivity.class));
            return true;
        }else if(id == R.id.cargo_tracking){
            startActivity(new Intent(getApplicationContext(),Cargo_tracking.class));
            return true;
        }
        else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,MainActivity.class));
            return true;
        }else if (id== R.id.btn_back) {
            startActivity(new Intent(getApplicationContext(), TryThisActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
