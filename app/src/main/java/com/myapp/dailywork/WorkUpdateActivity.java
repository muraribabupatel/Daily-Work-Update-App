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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WorkUpdateActivity extends AppCompatActivity {
    ImageView btn;
    EditText dateEdt,nameEdt,activityEdt,dayOutEdt,linkEdt,commentEdt;
    TextView submitTxt;
    FirebaseAuth auth;
    FirebaseFirestore db;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_update);
        dateEdt=findViewById(R.id.date);
        nameEdt=findViewById(R.id.name_Edt);
        activityEdt=findViewById(R.id.activityName);
        dayOutEdt=findViewById(R.id.dayOutput);
        linkEdt=findViewById(R.id.link);
        commentEdt=findViewById(R.id.comment);
        btn=findViewById(R.id.backArrow);
        submitTxt=findViewById(R.id.submitBtn);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("We're sending your Work Update...");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wDate=dateEdt.getText().toString();
                String wName=nameEdt.getText().toString();
                String wActivity=activityEdt.getText().toString();
                String wDayOut=dayOutEdt.getText().toString();
                String wLink=linkEdt.getText().toString();
                String wComment=commentEdt.getText().toString();
                if (TextUtils.isEmpty(wDate)){
                    Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(wName)){
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(wActivity)){
                    Toast.makeText(getApplicationContext(), "Enter Activity Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(wDayOut)){
                    Toast.makeText(getApplicationContext(), "Enter DayOut", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(wLink)){
                    Toast.makeText(getApplicationContext(), "Enter Link", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(wComment)){
                    Toast.makeText(getApplicationContext(), "Enter Comment", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,Object> work=new HashMap<>();
                work.put("date",wDate);
                work.put("name",wName);
                work.put("activityName",wActivity);
                work.put("dayOut",wDayOut);
                work.put("link",wLink);
                work.put("comment",wComment);
                
                dialog.show();
               auth.createUserWithEmailAndPassword(wName,wComment).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uid=task.getResult().getUser().getUid();
                            db.collection("users")
                                    .document(uid)
                                    .set(work).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        dialog.dismiss();
                                        Toast.makeText(WorkUpdateActivity.this, "Successfully sent your data ", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(WorkUpdateActivity.this,SuccessActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(WorkUpdateActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }

                    }
                });
                startActivity(new Intent(WorkUpdateActivity.this, SuccessActivity.class));


            }
        });

    }
}