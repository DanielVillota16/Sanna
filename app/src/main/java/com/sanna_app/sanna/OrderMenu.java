package com.sanna_app.sanna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.sanna_app.sanna.list.OrderItemProvider.OrderItemAdapter;
import com.sanna_app.sanna.list.OrderProvider.OrderProviderAdapter;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

public class OrderMenu extends AppCompatActivity implements View.OnClickListener, OrderProviderAdapter.OnOrderClickListener{

    private RecyclerView orders;
    private FirebaseFirestore db;
    private OrderProviderAdapter adapter;
    private FirebaseAuth auth;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        orders=findViewById(R.id.OrderProviderRecyclerView);
        back=findViewById(R.id.OrderProviderBackBtn);

        adapter=new OrderProviderAdapter();
        adapter.setListener(this);
        orders.setAdapter(adapter);
        orders.setHasFixedSize(true);
        LinearLayoutManager manager= new LinearLayoutManager(this);
        orders.setLayoutManager(manager);
        back.setOnClickListener(
                t->{
                    Intent i= new Intent(this,ProviderHome.class);
                    startActivity(i);
                    finish();
                }
        );
        loadOrders();
    }

    private void loadOrders() {
        Query orderRef = db.collection("orders").whereEqualTo("providerId",auth.getCurrentUser().getUid());
        orderRef.get().addOnCompleteListener(
                task -> {
                    adapter.clear();
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        Order docOrder=doc.toObject(Order.class);
                        adapter.addOrder(docOrder);
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnOrderClick(Order o) {
        Intent i=new Intent(this,order_provider_summary.class);
        i.putExtra("order",o);
        startActivity(i);
    }

}