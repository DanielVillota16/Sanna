package com.sanna_app.sanna.product.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.sanna_app.sanna.R;

import com.sanna_app.sanna.client.order.CurrentOrders;
import com.sanna_app.sanna.model.Product;

import org.w3c.dom.Text;

public class ProductDetails extends AppCompatActivity implements View.OnClickListener {

    private Product product;
    private ImageView productPicture;
    private TextView productName;
    private TextView productDescription;
    private TextView productPrice;
    private Button addToCartBtn;
    private CurrentOrders currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        product = (Product) getIntent().getSerializableExtra("Product");

        GetViewReferences();

        FirebaseStorage.getInstance().getReference().child("products").child(product.getId()).getDownloadUrl()
                .addOnCompleteListener(
                        task -> {
                            if(task.isSuccessful()){
                                String url = task.getResult().toString();
                                Glide.with(this).load(url).into(productPicture);
                                Log.i("URL: ",url);
                            }
                        });
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText("$" + product.getPrice());

        currentOrder = CurrentOrders.getInstance();
        addToCartBtn.setOnClickListener(this);

    }//closes onCreate method


    private void GetViewReferences() {
        productPicture = findViewById(R.id.productDetail_productPicture);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        productName = findViewById(R.id.productDetails_productName);
        productDescription = findViewById(R.id.productDetails_productDescription);
        productPrice = findViewById(R.id.productDetails_productPrice);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.addToCartBtn:
                currentOrder.getOrders().get(product.getProvider()).getProducts().add(product);
                break;
        }
    }
}//closes productDetails class