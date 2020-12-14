package com.sanna_app.sanna.list.OrderProvider;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;

public class OrderPViewModel extends RecyclerView.ViewHolder {

    private Button OrderBtn;
    private TextView tittle;
    private ImageView iv;

    public OrderPViewModel(@NonNull View itemView) {
        super(itemView);
        tittle=itemView.findViewById(R.id.OrderNumberTV);
        OrderBtn=itemView.findViewById(R.id.OrderProviderRowbtn);
        iv=itemView.findViewById(R.id.imageView2);

    }

    public Button getOrderBtn() {
        return OrderBtn;
    }

    public TextView getTittle() {
        return tittle;
    }

    public ImageView getIv() {
        return iv;
    }
}
