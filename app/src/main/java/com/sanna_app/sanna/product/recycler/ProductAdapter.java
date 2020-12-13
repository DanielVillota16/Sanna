package com.sanna_app.sanna.product.recycler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.client.catalogue.ClientCatalogue;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductView> implements ProductView.OnProductChosen {


    private ArrayList<Product> products;
    private Fragment parent;

    public ProductAdapter(Fragment parent) {
        products = new ArrayList<Product>();
        this.parent = parent;
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
        View row = inflater.inflate(R.layout.client_product_view, parent, false);
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
        holder.setProduct(_product);
        holder.setListener(this);

    }//closes onBindViewHolder method

    @Override
    public int getItemCount() {
        return products.size();
    }//closes getItemCount method


    @Override
    public void GoToProductDetails(Product product) {
        Intent toDetails = new Intent(parent.getActivity(), ProductDetails.class);
        toDetails.putExtra("Product", product);
        parent.startActivity(toDetails);
    }//closes GoToProductDetails method
}//closes ProductAdapter class
