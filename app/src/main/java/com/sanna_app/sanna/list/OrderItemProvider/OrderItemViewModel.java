package com.sanna_app.sanna.list.OrderItemProvider;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;

public class OrderItemViewModel extends RecyclerView.ViewHolder{

    private TextView name,quantity,price;
    public OrderItemViewModel(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.irfName);
        quantity=itemView.findViewById(R.id.irfQuantity);
        price= itemView.findViewById(R.id.irfPrice);
    }

    public TextView getName() {
        return name;
    }

    public TextView getQuantity() {
        return quantity;
    }

    public TextView getPrice() {
        return price;
    }

}
