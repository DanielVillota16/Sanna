package com.sanna_app.sanna.list.OrderProvider;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.Order;

import java.util.ArrayList;

public class OrderProviderAdapter extends RecyclerView.Adapter<OrderPViewModel> {

    private ArrayList<Order> orders;
    private OnOrderClickListener listener;

    public OrderProviderAdapter(){
        orders=new ArrayList<>();
    }

    public void addOrder(Order o){
        orders.add(o);
        notifyDataSetChanged();
    }

    public void clear(){
        orders.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderPViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.orderproviderrow,parent,false);
        OrderPViewModel opvm= new OrderPViewModel(view);
        return opvm;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderPViewModel holder, int position) {
        holder.getTittle().setText("Orden #"+(position+1));
        if(orders.get(position).getStatus()==0){
            holder.getOrderBtn().setOnClickListener(
                    v->listener.OnOrderClick(orders.get(position))
            );
        }else{
            holder.getIv().setBackgroundResource(R.drawable.check);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setListener(OnOrderClickListener listener){this.listener=listener;}

    public interface OnOrderClickListener{
        void OnOrderClick(Order o);
    }
}
