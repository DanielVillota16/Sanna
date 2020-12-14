package com.sanna_app.sanna.client.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.User;
import com.sanna_app.sanna.order.recycler.OrderAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ClientOrders extends Fragment {

    private FirebaseFirestore db;
    private RecyclerView clientOrdersList;
    private LinearLayoutManager layoutManager;
    private OrderAdapter orderAdapter;
    private String idCurrentUser;
    private FirebaseAuth auth;
    private Button goToCartBtn;

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

        clientOrdersList = root.findViewById(R.id.clientOrdersList);
        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        orderAdapter = new OrderAdapter(this);
        clientOrdersList.setLayoutManager(layoutManager);
        clientOrdersList.setAdapter(orderAdapter);
        goToCartBtn = root.findViewById(R.id.goToCartBtn);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        idCurrentUser = auth.getCurrentUser().getUid();

        goToCartBtn.setOnClickListener((v) -> {
            Intent i = new Intent(getContext(), ShoppingCartActivity.class);
            startActivity(i);
        });
        getOrders();
        return root;
    }//closes inflater method

    public void getOrders(){
        db.collection("orders").whereEqualTo("clientId", idCurrentUser).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                ArrayList<Order> orders = new ArrayList<>();
                for(DocumentSnapshot doc : task.getResult()){
                    Order order = doc.toObject(Order.class);
                    orders.add(order);
                }
                orderAdapter.addOrders(orders);
            } else
                Toast.makeText(getContext(), "No se ha podido cargar las ordenes", Toast.LENGTH_SHORT).show();
        });

    }


}//closes ClientCatalogue class