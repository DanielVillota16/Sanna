package com.sanna_app.sanna.product.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Product;

import org.w3c.dom.Text;

public class ProductDetails extends AppCompatActivity {

    private Product product;
    private ImageView productPicture;
    private TextView productName;
    private TextView productDescription;
    private TextView productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        product = (Product) getIntent().getSerializableExtra("Product");

        GetViewReferences();

        Glide.with(this).load(product.getPhoto()).into(productPicture);
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText("$" + product.getPrice());


    }//closes onCreate method


    private void GetViewReferences() {
        productPicture = findViewById(R.id.productDetail_productPicture);

        productName = findViewById(R.id.productDetails_productName);
        productDescription = findViewById(R.id.productDetails_productDescription);
        productPrice = findViewById(R.id.productDetails_productPrice);

    }


}//closes productDetails class