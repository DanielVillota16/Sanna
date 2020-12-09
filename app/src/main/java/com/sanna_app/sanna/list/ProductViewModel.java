package com.sanna_app.sanna.list;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;

public class ProductViewModel extends RecyclerView.ViewHolder{

    private Button productBtn;
    private TextView pName,pPrice;
    private ImageView pPhoto;
    public ProductViewModel(@NonNull View itemView) {
        super(itemView);
        productBtn=itemView.findViewById(R.id.ProductRowButton);
        pPhoto=itemView.findViewById(R.id.ProductIV);
        pName  =itemView.findViewById(R.id.ProductNameTV);
        pPrice=itemView.findViewById(R.id.ProductPriceTV);
    }

    public Button getProductBtn() {
        return productBtn;
    }

    public TextView getpName() {
        return pName;
    }

    public TextView getpPrice() {
        return pPrice;
    }

    public ImageView getpPhoto() {
        return pPhoto;
    }
}
