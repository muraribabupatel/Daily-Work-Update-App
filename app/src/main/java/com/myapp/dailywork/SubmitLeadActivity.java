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

public class SubmitLeadActivity extends AppCompatActivity {

    ImageView backBtn;
    EditText date,empName,leadName,mobileNo,emailId,product,budget;
    TextView submit;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_lead);
        date=findViewById(R.id.date);
        emailId=findViewById(R.id.email);
        empName=findViewById(R.id.employeeName);
        leadName=findViewById(R.id.leadName);
        mobileNo=findViewById(R.id.mobileNo);
        product=findViewById(R.id.product);
        budget=findViewById(R.id.budget);
        backBtn=findViewById(R.id.backArrow);
        submit=findViewById(R.id.submitBtn);
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lDate=date.getText().toString();
                String lEmail=emailId.getText().toString();
                String lEmpName=empName.getText().toString();
                String lLeadName=leadName.getText().toString();
                String lMobile=mobileNo.getText().toString();
                String lProduct=product.getText().toString();
                String lBudget=budget.getText().toString();
                if (TextUtils.isEmpty(lDate)) {
                    Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                    if (TextUtils.isEmpty(lEmail)){
                        Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                    }
                    if (TextUtils.isEmpty(lEmpName)){
                        Toast.makeText(getApplicationContext(), "Enter EmpName", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(lLeadName)){
                        Toast.makeText(getApplicationContext(), "Enter Lead Name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(lMobile)){
                        Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(lProduct)){
                        Toast.makeText(getApplicationContext(), "Enter Product", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(lBudget)){
                        Toast.makeText(getApplicationContext(), "Enter Your Budget", Toast.LENGTH_SHORT).show();
                        return;
                    }
                Map<String,Object> lead=new HashMap<>();
                    lead.put("date",lDate);
                    lead.put("employeeName",lEmpName);
                    lead.put("leadName",lLeadName);
                    lead.put("email",lEmail);
                    lead.put("mobile",lMobile);
                    lead.put("product",lProduct);
                    lead.put("budget",lBudget);
                    dialog.show();
                    mAuth.createUserWithEmailAndPassword(lEmail,lMobile).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                              String uid=task.getResult().getUser().getUid();
                             database.collection("users")
                            .document(uid)
                                     .set(lead).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if(task.isSuccessful()) {
                                         dialog.dismiss();
                                         startActivity(new Intent(SubmitLeadActivity.this, SuccessActivity.class));
                                         finish();
                                     } else {
                                         Toast.makeText(SubmitLeadActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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