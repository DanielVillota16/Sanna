package com.sanna_app.sanna.order.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;

public class OrderView extends RecyclerView.ViewHolder {

    private ConstraintLayout root;
    private TextView orderIdTV;
    private OnOrderClicked listener;
    private Order order;

    public OrderView(ConstraintLayout root) {
        super(root);
        this.root = root;
        this.orderIdTV = root.findViewById(R.id.orderIdTV);
        this.root.setOnClickListener(this::openOrder);
    }

    private void openOrder(View view) {
        listener.goToOrder(order);
    }


    public interface OnOrderClicked{
        void goToOrder(Order order);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public OnOrderClicked getListener() {
        return listener;
    }

    public Order getOrder() {
        return order;
    }

    public void setListener(OnOrderClicked listener) {
        this.listener = listener;
    }

    public TextView getOrderIdTV() {
        return orderIdTV;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
