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
import com.sanna_app.sanna.list.OrderItemProvider.OrderItemAdapter;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

public class OrderProviderSummary extends AppCompatActivity implements View.OnClickListener, OrderItemAdapter.OnItemClickListener{

    private TextView title, orderValue;
    private RecyclerView items;
    private Button accept, wait;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private OrderItemAdapter oia;
    private String oId;
    private Double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_provider_summary);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        value=0.0;
        oId= (String) getIntent().getExtras().get("order");

        title=findViewById(R.id.titleOrderProvET);
        orderValue=findViewById(R.id.priceOrderProvET);
        items=findViewById(R.id.ProdOrderProvRV);
        wait=findViewById(R.id.cancelOrderProviderBtn);
        accept=findViewById(R.id.acceptOrderProviderBtn);

        oia = new OrderItemAdapter();
        oia.setListener(this);
        items.setHasFixedSize(true);
        items.setAdapter(oia);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        items.setLayoutManager(manager);
        wait.setOnClickListener(this);
        accept.setOnClickListener(this);

        loadItems();
        runOnUiThread(
                ()->{
                    title.setText("Resumen de la Orden");
                    orderValue.setText("$"+value);
                }
        );

    }

    private void loadItems() {
        value=0.0;
        Query itemRef=db.collection("orders").document(oId).collection("products");
        itemRef.get().addOnCompleteListener(
                task -> {
                    if(task.isSuccessful()){
                        oia.clear();
                        for(QueryDocumentSnapshot doc:task.getResult()){
                            Product prodOrder=doc.toObject(Product.class);
                            value+=prodOrder.getPrice()*prodOrder.getQuantity();
                            oia.addItem(prodOrder);
                        }
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancelOrderProviderBtn:
                Intent i= new Intent(this,OrderMenu.class);
                startActivity(i);
                break;
                
            case R.id.acceptOrderProviderBtn:
                editOrder();
                Intent j= new Intent(this,OrderMenu.class);
                startActivity(j);
                break;
        }
    }

    private void editOrder() {
    }

    @Override
    public void OnItemClick(Order o) {

    }
}