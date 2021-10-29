package com.myapp.dailywork.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.myapp.dailywork.databinding.FragmentLeadBinding;
import com.myapp.dailywork.R;
import com.myapp.dailywork.SubmitLeadActivity;

public class LeadFragment extends Fragment {
    TextView lead;

    FragmentLeadBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentLeadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lead=root.findViewById(R.id.submit_lead);
        lead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SubmitLeadActivity.class));
            }
        });
        return root;

    }


}