package com.sanna_app.sanna.product.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;

public class ProductView extends RecyclerView.ViewHolder {

    private ConstraintLayout root;
    private ImageView productPic;
    private TextView productName;
    private TextView productPrice;

    public ProductView(ConstraintLayout root) {
        super(root);
        this.root = root;
        productPic = root.findViewById(R.id.productView_image);
        productName = root.findViewById(R.id.productView_name);
        productPrice = root.findViewById(R.id.productView_price);

    }//closes ProductView constructor

    public ConstraintLayout getRoot() {
        return root;
    }//closes getRoot method

    public ImageView getProductPic() {
        return productPic;
    }//closes getProductPic method

    public TextView getProductName() {
        return productName;
    }//closes getProductName method

    public TextView getProductPrice() {
        return productPrice;
    }//closes getProductPrice method

}//closes ProductView class
