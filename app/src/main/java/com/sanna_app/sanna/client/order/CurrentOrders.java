package com.sanna_app.sanna.client.order;

import com.sanna_app.sanna.model.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentOrders {

    private static CurrentOrders currentOrders;

    private HashMap<String, Order> orders;

    private CurrentOrders(){
        this.orders = new HashMap<>();
    }

    public static CurrentOrders getInstance(){
        if(currentOrders == null) {
            currentOrders = new CurrentOrders();
        }
        return currentOrders;
    }

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public void setOrder(HashMap<String, Order> orders) {
        this.orders = orders;
    }
}
