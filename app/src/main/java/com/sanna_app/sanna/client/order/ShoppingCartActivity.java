package com.sanna_app.sanna.client.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    private Button buyBtn;
    private ArrayList<String> products;
    private ListView productsList;
    private ArrayAdapter<String> adapter;
    private CurrentOrders currentOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        buyBtn = findViewById(R.id.buyBtn);
        productsList = findViewById(R.id.productsList);

        currentOrdersList = CurrentOrders.getInstance();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);

    }
}