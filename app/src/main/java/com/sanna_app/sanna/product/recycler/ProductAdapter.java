package com.sanna_app.sanna.product.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductView> {


    private ArrayList<Product> products;

    public ProductAdapter() {
        products = new ArrayList<Product>();
    }//closes ProductAdapter constructor

    public ArrayList<Product> getProducts() {
        return products;
    }//closes products method

    public void AddProduct(Product product) {
        products.add(product);
        this.notifyDataSetChanged();
    }//closes AddProduct class

    @NonNull
    @Override
    public ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.client_product_view, parent,false);
        ConstraintLayout rowRoot = (ConstraintLayout) row;
        ProductView productView = new ProductView(rowRoot);


        return productView;
    }//closes onCreateViewHolder method

    @Override
    public void onBindViewHolder(@NonNull ProductView holder, int position) {
        Product _product = products.get(position);

        Glide.with(holder.getProductPic().getContext()).load(_product.getPhoto()).into(holder.getProductPic());
        holder.getProductName().setText(_product.getName());
        holder.getProductPrice().setText(String.valueOf(_product.getPrice()));

    }//closes onBindViewHolder method

    @Override
    public int getItemCount() {
        return products.size();
    }//closes getItemCount method


}//closes ProductAdapter class
