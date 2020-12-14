package com.sanna_app.sanna.client.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;

import java.util.ArrayList;
import java.util.Collection;

public class ShoppingCartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Button buyBtn;
    private ListView ordersList;
    private ArrayList<Order> orders;
    private ArrayAdapter<Order> adapter;
    private CurrentOrders currentOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        buyBtn = findViewById(R.id.orderAllBtn);
        ordersList = findViewById(R.id.ordersList);
        ordersList = findViewById(R.id.ordersList);

        currentOrdersList = CurrentOrders.getInstance();
        orders = new ArrayList<>(currentOrdersList.getOrders().values());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orders);

        ordersList.setAdapter(adapter);
        ordersList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Order order = orders.get(position);
        Intent i = new Intent(this, OrderFromCartActivity.class);
        i.putExtra("order", order);
        startActivity(i);
    }
}