package com.myapp.dailywork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;


import com.myapp.dailywork.fragment.AwiskarTechFragment;
import com.myapp.dailywork.fragment.LeadFragment;
import com.myapp.dailywork.fragment.ProfileFragment;
import com.myapp.dailywork.fragment.RequestFragment;
import com.myapp.dailywork.fragment.WorkUpdateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView=findViewById(R.id.dropdown_menu);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);

        //bottomNavigationView.setBackground(null);

        bottomNavigationView.getMenu().getItem(0).setEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,new AwiskarTechFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;

                switch (item.getItemId())
                {
                    case R.id.navigation_awiskar_tech: temp = new AwiskarTechFragment();
                        break;
                    case R.id.navigation_lead: temp = new LeadFragment();
                        break;



                    case R.id.navigation_request: temp = new RequestFragment();
                    break;
                    case R.id.navigation_work_update: temp = new WorkUpdateFragment();
                        break;
                    case R.id.navigation_price: temp = new ProfileFragment();
                        break;


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,temp).commit();
                return true;
            }
        });

    }
}