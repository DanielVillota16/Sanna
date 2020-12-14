package com.sanna_app.sanna.client.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    private ListView detailsList;
    private Order order;
    private TextView id, destName, addressFrom, addressTo, status;
    private ArrayList<String> products;
    private ArrayAdapter<String> adapter;

    private ListenerRegistration listener;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order = (Order) getIntent().getExtras().get("order");
        detailsList = findViewById(R.id.ordersList);
        id = findViewById(R.id.clientOrderIdTV);
        destName = findViewById(R.id.clientOrderDestNameTV);
        addressFrom = findViewById(R.id.clientOrderAddressFromTV);
        addressTo = findViewById(R.id.clientOrderAddressToTV);
        status = findViewById(R.id.clientOrderStatusTV);
        products = new ArrayList<>();
        for(Product product : order.getProducts()) products.add(product.getName() + " : " + product.getQuantity());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        detailsList.setAdapter(adapter);
        id.setText(order.getId());
        destName.setText(order.getDestName());
        addressFrom.setText(order.getAddressFrom().toString());
        addressTo.setText(order.getAddressTo().toString());
        listenToOrderStatus();
    }

    public void listenToOrderStatus(){
        db = FirebaseFirestore.getInstance();
        listener = db.collection("orders").document(order.getId()).addSnapshotListener((value, error) -> {
            order = value.toObject(Order.class);
            status.setText(order.getStatus());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listener.remove();
    }
}