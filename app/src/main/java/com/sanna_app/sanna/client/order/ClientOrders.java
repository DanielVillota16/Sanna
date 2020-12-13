package com.sanna_app.sanna.client.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanna_app.sanna.R;

public class ClientOrders extends Fragment {


    public static ClientOrders newInstance() {
        ClientOrders fragment = new ClientOrders();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }//closes ClientOrders method


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_client_orders, container, false);

        return root;
    }//closes inflater method


}//closes ClientCatalogue class