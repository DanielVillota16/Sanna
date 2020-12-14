package com.sanna_app.sanna.client.catalogue;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.sanna_app.sanna.ClientHome;
import com.sanna_app.sanna.DeliveryHome;
import com.sanna_app.sanna.ProviderHome;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.constants.Constants;
import com.sanna_app.sanna.model.Product;
import com.sanna_app.sanna.model.User;
import com.sanna_app.sanna.order.recycler.OrderAdapter;
import com.sanna_app.sanna.product.recycler.ProductAdapter;

public class ClientCatalogue extends Fragment {

    private FirebaseFirestore db;
    private ListenerRegistration listener;

    private RecyclerView catalogueList;
    private LinearLayoutManager layoutManager;
    private ProductAdapter productAdapter;


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
        View root = inflater.inflate(R.layout.fragment_client_catalogue, container, false);


        db = FirebaseFirestore.getInstance();

        catalogueList = root.findViewById(R.id.client_catalogueRV);

        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        productAdapter = new ProductAdapter(this);

        catalogueList.setLayoutManager(layoutManager);
        catalogueList.setAdapter(productAdapter);

        getProducts();

        return root;
    }//closes inflater method

    private void getProducts() {

        Query query = db.collection("products");
        listener = query.addSnapshotListener((data, error) -> {

            for (DocumentSnapshot doc : data.getDocuments()) {
                Product product = doc.toObject(Product.class);
                if (!productAdapter.getProducts().contains(product))
                    productAdapter.AddProduct(product);
            }
        });
    }//closes getProducts methos


    @Override
    public void onDestroy() {
        super.onDestroy();
        listener.remove();
    }
}//closes ClientCatalogue class