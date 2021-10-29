package com.myapp.dailywork.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.dailywork.R;
import com.myapp.dailywork.RequestActivity;
import com.myapp.dailywork.databinding.FragmentRequestBinding;

public class RequestFragment extends Fragment {
 FragmentRequestBinding binding;
TextView textView;
    public RequestFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRequestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textView=root.findViewById(R.id.request);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RequestActivity.class));
            }
        });
        return root;
    }
}