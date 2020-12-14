package com.sanna_app.sanna.order.recycler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.client.order.OrderDetailsActivity;
import com.sanna_app.sanna.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderView> implements OrderView.OnOrderClicked {

    private ArrayList<Order> orders;
    private Fragment parent;

    public OrderAdapter(Fragment parent) {
        orders = new ArrayList<>();
        this.parent = parent;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
        this.notifyDataSetChanged();
    }

    public void addOrders(List<Order> orders){
        this.orders.addAll(orders);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.client_order_row, parent, false);
        ConstraintLayout rowRoot = (ConstraintLayout) row;
        OrderView orderView = new OrderView(rowRoot);
        return orderView;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderView holder, int position) {
        Order order = orders.get(position);
        String text = "ID:" + order.getId() + " Estado de la orden: " + Order.statusToString(order.getStatus());
        holder.getOrderIdTV().setText(text);
        holder.setOrder(order);
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }//closes getItemCount method


    @Override
    public void goToOrder(Order order) {
        Intent intent = new Intent(parent.getContext(), OrderDetailsActivity.class);
        intent.putExtra("order", order);
        parent.startActivity(intent);
    }

}
