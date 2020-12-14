package com.sanna_app.sanna.client.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    private ListView detailsList;
    private Order order;
    private ArrayList<String> details;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order = (Order) getIntent().getExtras().get("order");
        detailsList = findViewById(R.id.detailsList);
        details = new ArrayList<>();
        details.add("ID: " + order.getId());
        details.add("Destinatario: " + order.getDestName());
        details.add("Dirección de la farmacia: " + order.getAddressFrom());
        details.add("Dirección de destino: " + order.getAddressTo());
        details.add("Estado de la orden: " + Order.statusToString(order.getStatus()));
        details.add("Productos:\n");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, details);
        searchProducts(order.getProducts());
    }

    void searchProducts(ArrayList<Product> products){
        FirebaseFirestore.getInstance().collection("products").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for(DocumentSnapshot doc : task.getResult()){
                    Product product = doc.toObject(Product.class);
                    if(products.contains(product)){
                        details.set(4, details.get(4) + product.getQuantity() + " | " + product.getName() + "\n") ;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

}