package com.myapp.dailywork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;



public class DomainRegistration extends AppCompatActivity {
WebView webView;
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_registration);
        back=findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView=findViewById(R.id.webView);

        webView.loadUrl("https://www.awiskartech.com/whmcs/");
        webView.setWebViewClient(new CheckWebsite.WebViewController());

        return;
    }


}