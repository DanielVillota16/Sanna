package com.sanna_app.sanna.list.OrderItemProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.list.OrderProvider.OrderProviderAdapter;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemViewModel> {

    private ArrayList<Product> items;
    private OnItemClickListener listener;

    public void addItem(Product p){
        items.add(p);
        notifyDataSetChanged();
    }

    public  void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderItemViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_row_fragment,parent,false);
        OrderItemViewModel oivm=new OrderItemViewModel(view);
        return oivm;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewModel holder, int position) {
        holder.getName().setText(items.get(position).getName());
        holder.getQuantity().setText(""+items.get(position).getQuantity());
        holder.getPrice().setText("$"+items.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(OnItemClickListener listener){this.listener=listener;}

    public interface OnItemClickListener{
        void OnItemClick(Order o);
    }
}
