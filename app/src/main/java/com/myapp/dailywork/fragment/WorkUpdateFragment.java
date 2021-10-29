package com.myapp.dailywork.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.dailywork.WorkUpdateActivity;
import com.myapp.dailywork.R;
import com.myapp.dailywork.databinding.FragmentWorkUpdateBinding;


public class WorkUpdateFragment extends Fragment {

            FragmentWorkUpdateBinding binding;
            TextView update;
    public WorkUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentWorkUpdateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        update=root.findViewById(R.id.update_work);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WorkUpdateActivity.class));
            }
        });

        return root;
    }
}