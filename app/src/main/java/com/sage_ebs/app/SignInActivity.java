package com.sage_ebs.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity  {


    private EditText email, password;
    private TextView signIn, reset;
    private Button login;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Database;
    private DatabaseReference myRef;
    private Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        email = findViewById(R.id.ed_mail);
        password = findViewById(R.id.ed_password);
        signIn = findViewById(R.id.tv_signin);
        reset = findViewById(R.id.tv_reset);
        login = findViewById(R.id.btn_login);
        toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResetPasswordActivity.class));
            }
        });
        mAuth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance();
        myRef = Database.getReference("message");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

    }

    private void signInUser() {
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (mail.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
        }else if (pass.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
        }else if (!(mail.isEmpty() && pass.isEmpty())){
        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (mAuth.getCurrentUser().isEmailVerified()){
                        Log.d("SignInActivity","signInWithEmail:success");
                        //Toast.makeText(SignInActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TryThisActivity.class));
                        FirebaseUser user = mAuth.getCurrentUser();
                        saveUserInfo(user);
                    } else{
                        Toast.makeText(SignInActivity.this, "Please verify email address", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Log.w("SignInActivity", "signInWithEmail:failure",task.getException());
                    Toast.makeText(SignInActivity.this,"Incorrect email/password",Toast.LENGTH_SHORT).show();
                    saveUserInfo(null);
                }
            }
        });
    }
    }

    private void saveUserInfo(FirebaseUser user) {
        myRef.setValue(user);
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
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
