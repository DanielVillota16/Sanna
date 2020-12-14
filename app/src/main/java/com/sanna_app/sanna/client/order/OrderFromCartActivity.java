package com.sanna_app.sanna.client.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class OrderFromCartActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView detailsList;
    private Order order;
    private TextView id, destName, addressFrom, addressTo, status;
    private Button makeOrder, discard;
    private ArrayList<String> products;
    private ArrayAdapter<String> adapter;
    private CurrentOrders currentOrders;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_from_cart);
        order = (Order) getIntent().getExtras().get("order");
        detailsList = findViewById(R.id.cartList);
        id = findViewById(R.id.clientCartOrderIdTV);
        destName = findViewById(R.id.clientCartOrderDestNameTV);
        addressFrom = findViewById(R.id.clientCartOrderAddressFromTV);
        addressTo = findViewById(R.id.clientCartOrderAddressToTV);
        status = findViewById(R.id.clientCartOrderStatusTV);
        makeOrder = findViewById(R.id.clientMakeOrderBtn);
        discard = findViewById(R.id.clientDiscardOrderBtn);
        products = new ArrayList<>();
        currentOrders = CurrentOrders.getInstance();
        for(Product product : order.getProducts()) products.add(product.getName() + " : " + product.getQuantity());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        detailsList.setAdapter(adapter);
        id.setText(order.getId());
        destName.setText(order.getDestName());
        addressFrom.setText(order.getAddressFrom().toString());
        addressTo.setText(order.getAddressTo().toString());
        status.setText(order.getStatus());

        makeOrder.setOnClickListener(this);
        discard.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clientDiscardOrderBtn:
                currentOrders.getOrders().remove(order.getProviderId());
                finish();
                break;
            case R.id.clientMakeOrderBtn:
                db.collection("orders").document(order.getId()).set(order).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                        Toast.makeText(this, "Pedido realizado con éxito", Toast.LENGTH_SHORT).show();
                    else
                        Log.e(">>>>", task.getException().getMessage());
                });
                break;
        }
    }
}