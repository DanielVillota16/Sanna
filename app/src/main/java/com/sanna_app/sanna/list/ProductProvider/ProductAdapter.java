package com.sanna_app.sanna.list.ProductProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewModel> {

    private ArrayList<Product> products;
    private OnProductClickListener listener;

    public ProductAdapter(){
        products = new ArrayList<>();
    }

    public void addProduct(Product p){
        products.add(p);
        notifyDataSetChanged();
    }

    public void clear(){
        products.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.productrow,parent,false);
        ProductViewModel pvm= new ProductViewModel(view);
        return pvm;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewModel holder, int position) {
        holder.getpName().setText(products.get(position).getName());
        holder.getpPrice().setText("$"+products.get(position).getPrice());
        holder.getProductBtn().setOnClickListener(v->{listener.onProductClick(products.get(position));});
        FirebaseStorage.getInstance().getReference().child("products").child(products.get(position).getId()).getDownloadUrl()
                .addOnCompleteListener(
                        task -> {
                            String url=task.getResult().toString();
                            Glide.with(holder.getpPhoto()).load(url).into(holder.getpPhoto());
                        }
                );
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setListener(OnProductClickListener listener){this.listener=listener;}

    public interface OnProductClickListener{
        void onProductClick(Product p);
    }
}
