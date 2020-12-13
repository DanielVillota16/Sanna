package com.sanna_app.sanna.client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanna_app.sanna.R;

public class ClientCatalogue extends Fragment {


    public static ClientCatalogue newInstance() {
        ClientCatalogue fragment = new ClientCatalogue();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }//closes ClientCatalogue method


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_catalogue, container, false);
    }//closes inflater method


}//closes ClientCatalogue class