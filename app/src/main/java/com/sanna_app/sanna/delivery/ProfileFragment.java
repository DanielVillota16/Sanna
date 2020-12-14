package com.sanna_app.sanna.delivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

private Button btnlist;
private Button btnConfig;
private User user;
    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

       fragment.setArguments(args);
        return fragment;
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        String userS = getArguments().getString("user");
        Gson gson = new Gson();
        user = gson.fromJson(userS,User.class);


        btnlist =  root.findViewById(R.id.btnpedidos);
        btnConfig = root.findViewById(R.id.btnconfig);


        btnlist.setOnClickListener(
                (v)->{
                    Intent i = new Intent(getActivity(), ListaPedido.class);
                    startActivity(i);
                }
        );

        btnConfig.setOnClickListener(
                (v)->{
                    Intent i = new Intent(getActivity(), ConfigProfile.class);
                    i.putExtra("user", user);
                    startActivity(i);
                }
        );

        // Inflate the layout for this fragment
        return root;
    }




}