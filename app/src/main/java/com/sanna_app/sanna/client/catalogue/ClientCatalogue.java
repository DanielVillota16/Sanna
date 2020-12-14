package com.sanna_app.sanna.client.catalogue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Product;
import com.sanna_app.sanna.product.recycler.ProductAdapter;

public class ClientCatalogue extends Fragment {

    private FirebaseFirestore db;


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

        db.collection("products").get().addOnCompleteListener(
                task -> {
                    for (DocumentSnapshot doc : task.getResult()) {
                        Product product = doc.toObject(Product.class);


                            if (!productAdapter.getProducts().contains(product)) productAdapter.AddProduct(product);



                    }
                });


    }//closes getProducts methos


}//closes ClientCatalogue class