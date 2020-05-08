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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private EditText user, password, email;
    private TextView signIn;
    private Button register;
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Database;
    private DatabaseReference myRef;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user = findViewById(R.id.ed_user);
        password = findViewById(R.id.ed_password);
        email = findViewById(R.id.ed_email);
        register = findViewById(R.id.btn_register);
        signIn = findViewById(R.id.tv_signIn);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signInButton = findViewById(R.id.google_signIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance();
        myRef = Database.getReference("message");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String emailId = email.getText().toString().trim();

                if (username.isEmpty()){
                    user.setError("Please enter username");
                    user.requestFocus();
                }else if (pass.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();
                }else if (emailId.isEmpty()){
                    email.setError("Please enter email address");
                    email.requestFocus();
                } else if (!(emailId.isEmpty() && pass.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(emailId, pass)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Log.w( "SignUpActivity", "createUserWithEmail:success");
                                                    Toast.makeText(SignUpActivity.this,
                                                            "Registration successful, please check your mail for verification", Toast.LENGTH_SHORT).show();
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    saveUserInfo(user);
                                                }else {
                                                    Toast.makeText(SignUpActivity.this,
                                                            task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });

                                    }else {
                                        Log.w("SignUpActivity", "createUserWithEmail:failure",
                                                task.getException());
                                   Toast.makeText(SignUpActivity.this, "Authentication failed",
                                           Toast.LENGTH_SHORT).show();
                                   saveUserInfo(null);
                                    }
                                }
                            });
                }else{
                    Toast.makeText(SignUpActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
