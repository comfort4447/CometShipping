package com.sage_ebs.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private Button Continue, signin;
   // private FirebaseAuth mAuth;
    private Toolbar toolbar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // mAuth = FirebaseAuth.getInstance();

        toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);

        Continue = findViewById(R.id.btn_continue);
        signin = findViewById(R.id.btn_signin);


        toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

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
            startActivity(new Intent(getApplicationContext(),Cargo_tracking.class));
            return true;
        }
        else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,MainActivity.class));
            return true;
        }else if (id== R.id.btn_back) {
            startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
