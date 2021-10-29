package com.myapp.dailywork.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.dailywork.CheckWebsite;
import com.myapp.dailywork.DomainRegistration;
import com.myapp.dailywork.HoastingPlan;
import com.myapp.dailywork.PriceList;
import com.myapp.dailywork.R;
import com.myapp.dailywork.databinding.FragmentAwiskarTechBinding;

public class AwiskarTechFragment extends Fragment {

       FragmentAwiskarTechBinding binding;
       TextView checkTxt,planTxt,domainTxt,priceTxt;

       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

           binding = FragmentAwiskarTechBinding.inflate(inflater, container, false);
           View root = binding.getRoot();
           checkTxt=root.findViewById(R.id.check_website);
          planTxt=root.findViewById(R.id.plans);
           domainTxt=root.findViewById(R.id.domain);
           priceTxt=root.findViewById(R.id.priceList);

           domainTxt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getContext(), DomainRegistration.class));
               }
           });
           priceTxt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getContext(), PriceList.class));
               }
           });

           checkTxt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   startActivity(new Intent(getContext(), CheckWebsite.class));
               }

           });
           planTxt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getContext(), HoastingPlan.class));
               }
           });

           return root;
    }
}