package com.myapp.dailywork.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.dailywork.databinding.FragmentPofileBinding;
import com.bumptech.glide.Glide;
import com.myapp.dailywork.LoginActivity;
import com.myapp.dailywork.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

FragmentPofileBinding binding;
CircleImageView imageView;
TextView name,email,logOut;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPofileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

          imageView=root.findViewById(R.id.imageView);
          name=root.findViewById(R.id.textViewName);
          email=root.findViewById(R.id.textViewEmail);
          logOut=root.findViewById(R.id.logOut);
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(getContext());
        name.setText(account.getDisplayName());
        email.setText(account.getEmail());
        Glide.with(this).load(account.getPhotoUrl()).into(imageView);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });


        return root;

    }
}