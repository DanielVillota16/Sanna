package com.sanna_app.sanna.client.delivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sanna_app.sanna.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

private Button btnlist;
private Button btnConfig;
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
                    startActivity(i);
                }
        );

        // Inflate the layout for this fragment
        return root;
    }




}