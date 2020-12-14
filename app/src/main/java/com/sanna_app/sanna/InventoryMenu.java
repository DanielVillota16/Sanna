package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.sanna_app.sanna.list.ProductProvider.ProductAdapter;
import com.sanna_app.sanna.model.Product;
import com.sanna_app.sanna.model.User;

public class InventoryMenu extends AppCompatActivity implements View.OnClickListener,ProductAdapter.OnProductClickListener{

    private User u;
    private RecyclerView products;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    private Button returnBtn, addBtn;
    private TextView tamano;
    private ProductAdapter adapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_menu);
        db=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
        auth= FirebaseAuth.getInstance();

        returnBtn=findViewById(R.id.InventoryReturnBtn);
        addBtn=findViewById(R.id.AddProductBtn);
        tamano=findViewById(R.id.InventoryQuantityTV);
        products=findViewById(R.id.InventoryRecyclerView);

        adapter=new ProductAdapter();
        adapter.setListener(this);
        products.setAdapter(adapter);
        products.setHasFixedSize(true);
        LinearLayoutManager manager= new LinearLayoutManager(this);
        products.setLayoutManager(manager);

        addBtn.setOnClickListener(this);
        returnBtn.setOnClickListener(this);

        loadProducts();
    }

    private void loadProducts() {
        Query productRef = db.collection("products").whereEqualTo("provider",auth.getCurrentUser().getUid());
        productRef.get().addOnCompleteListener(
                task -> {
                    adapter.clear();
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        Product docProduct=doc.toObject(Product.class);
                        adapter.addProduct(docProduct);
                    }
                    runOnUiThread(
                            ()->{
                                tamano.setText(adapter.getItemCount()+"");
                            }
                    );
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AddProductBtn:
                Intent i= new Intent(this,addProductActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.InventoryReturnBtn:
                Intent j= new Intent(this, ProviderHome.class);
                startActivity(j);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onProductClick(Product p) {
        Intent i= new Intent(this, EditProduct.class);
        i.putExtra("product", p);
<<<<<<< Updated upstream
        startActivity(i);*/
=======
        startActivity(i);

>>>>>>> Stashed changes
    }
}