package com.sanna_app.sanna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.sanna_app.sanna.model.Product;

import java.util.HashMap;
import java.util.Map;

public class EditProduct extends AppCompatActivity implements View.OnClickListener {
    private Product product;
    private FirebaseFirestore firestore;

    private ImageView suchproductImg;

    private EditText suchNameProductTxt;
    private EditText suchPriceProductTxt;
    private EditText suchDescriptioProductTxt;

    private Button deletProductProviderBtn;
    private Button safeProductBtn;
    private Button cancelSafeProductBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_provider);

        product = (Product)getIntent().getExtras().getSerializable("product");
        firestore = FirebaseFirestore.getInstance();

        suchproductImg = findViewById(R.id.suchproductImg);
        suchNameProductTxt = findViewById(R.id.suchNameProductTxt);
        suchPriceProductTxt = findViewById(R.id.suchPriceProductTxt);
        suchDescriptioProductTxt = findViewById(R.id.suchDescriptioProductTxt);
        deletProductProviderBtn = findViewById(R.id.deletProductProviderBtn);
        safeProductBtn = findViewById(R.id.safeProductBtn);
        cancelSafeProductBtn = findViewById(R.id.safeProductBtn);

        FirebaseStorage.getInstance().getReference().child("products").child(product.getId()).getDownloadUrl().addOnCompleteListener(
                task -> {
                    String url=task.getResult().toString();
                    Glide.with(suchproductImg).load(url).into(suchproductImg);
                }
        );

        suchNameProductTxt.setText(product.getName());
        suchPriceProductTxt.setText(product.getPrice()+"");
        suchDescriptioProductTxt.setText(product.getDescription());

        deletProductProviderBtn.setOnClickListener(this);
        safeProductBtn.setOnClickListener(this);
        cancelSafeProductBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.safeProductBtn:
                if(suchNameProductTxt.getText().length() > 0 && suchPriceProductTxt.getText().length() > 0 && suchDescriptioProductTxt.getText().length() > 0){
                    Map<String,Object> map =new HashMap<>();

                    map.put("description",suchDescriptioProductTxt.getText().toString());
                    map.put("id", product.getId());
                    map.put("name",suchNameProductTxt.getText().toString());
                    map.put("photo", product.getPhoto());
                    map.put("price",Double.parseDouble(suchPriceProductTxt.getText().toString()));
                    map.put("provider", product.getProvider());
                    map.put("quantity", product.getQuantity());


                    firestore.collection("products").document(product.getId()).update(map);
                    Intent i = new Intent(this, InventoryMenu.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(),"por favor no deje ningun campo vacio",Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }
}
