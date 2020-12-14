package com.sanna_app.sanna.product.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Product;

public class ProductView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ConstraintLayout root;
    private ImageView productPic;
    private TextView productName;
    private TextView productPrice;
    private OnProductChosen listener;
    private Product product;

    public ProductView(ConstraintLayout root) {
        super(root);
        this.root = root;
        root.setOnClickListener(this);
        productPic = root.findViewById(R.id.productView_image);
        productName = root.findViewById(R.id.productView_name);
        productPrice = root.findViewById(R.id.productView_price);

    }//closes ProductView constructor

    public void setProduct(Product product) {
        this.product = product;
    }

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


    public void setListener(OnProductChosen listener) {
        this.listener = listener;
    }//closes setlistener method

    @Override
    public void onClick(View v) {
        listener.GoToProductDetails(product);
    }//closes setlistener method


    public interface OnProductChosen {
        void GoToProductDetails(Product product);
    }//closes OnProductChoesen interface


}//closes ProductView class



