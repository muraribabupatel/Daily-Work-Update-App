package com.myapp.dailywork;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.myapp.dailywork.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    CircleImageView imageView;
    GoogleSignInClient mGoogleSignInClient;
    TextView textName, textEmail;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions gso;
    GoogleSignInApi mGoogleApiClient;
    FirebaseAuth mAuth;
    ImageView back;
TextView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
back=findViewById(R.id.back_btn);
        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textViewName);
        textEmail = findViewById(R.id.textViewEmail);
        button = findViewById(R.id.logOut);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()){
                            gotoMainActivity();
                        }else {
                            Toast.makeText(MainActivity.this, "Log Out failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    private void gotoMainActivity() {
    startActivity(new Intent(MainActivity.this,LoginActivity.class));
    finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private  void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            textName.setText(account.getDisplayName());
            textEmail.setText(account.getEmail());
            Picasso.get().load(account.getPhotoUrl()).placeholder(R.drawable.tech).into(imageView);
        }
        else {
            gotoMainActivity();
        }
    }
    protected void onStart(){
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}