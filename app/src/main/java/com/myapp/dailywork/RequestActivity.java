package com.myapp.dailywork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.dailywork.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {
    ImageView backBtn;
    EditText nameEdt,mobileEdt,emailEdt,cityEdt,serviceEdt;
    TextView submitBtn;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        nameEdt=findViewById(R.id.eName);
        mobileEdt=findViewById(R.id.mobileNo);
        emailEdt=findViewById(R.id.email);
        cityEdt=findViewById(R.id.city);
        serviceEdt=findViewById(R.id.services);
        backBtn=findViewById(R.id.backArrow);
        submitBtn=findViewById(R.id.submitBtn);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("We're sending your data...");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uEmail=emailEdt.getText().toString().trim();
                String uName=nameEdt.getText().toString().trim();
                String uMobile=mobileEdt.getText().toString().trim();
                String uService=serviceEdt.getText().toString().trim();
                String uCity=cityEdt.getText().toString().trim();
               
                if (TextUtils.isEmpty(uEmail)){
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
               
                if (TextUtils.isEmpty(uName)){
                    Toast.makeText(getApplicationContext(), "Enter  Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uMobile)){
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uCity)){
                    Toast.makeText(getApplicationContext(), "Enter City", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uService)){
                    Toast.makeText(getApplicationContext(), "Enter Services", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,Object> request=new HashMap<>();

                request.put("name",uName);
                request.put("email",uEmail);

                request.put("mobile",uMobile);
                request.put("service",uService);
                request.put("city",uCity);
                dialog.show();
                mAuth.createUserWithEmailAndPassword(uEmail,uMobile).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uid=task.getResult().getUser().getUid();
                            database.collection("users")
                                    .document(uid)
                                    .set(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        dialog.dismiss();
                                        startActivity(new Intent(RequestActivity.this, SuccessActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(RequestActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }

                    }
                });

               startActivity(new Intent(getApplicationContext(),SuccessActivity.class));
            }
        });
    }
}