package com.sanna_app.sanna.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.list.OrderProvider.OrderProviderAdapter;
import com.sanna_app.sanna.model.Order;

public class ListaPedido extends AppCompatActivity {

    private RecyclerView orders;
    private OrderProviderAdapter adapter;
    private GridLayoutManager layoutManager;
    private FirebaseFirestore db;
    private CollectionReference orderRef;
    // order
    private Order order;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);
	db=FirebaseFirestore.getInstance();
    orders = findViewById(R.id.ordersList);
    adapter=new OrderProviderAdapter();

    layoutManager = new GridLayoutManager(this,1);
    orders.setAdapter(adapter);
    orders.setLayoutManager(layoutManager);

        Query orderRef = db.collection("orders");
        orderRef.get().addOnCompleteListener(
                task -> {
                    adapter.clear();
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        Order docOrder=doc.toObject(Order.class);
                        adapter.addOrder(docOrder);
                    }
                }
        );

    }

    private void updateOrders() {

        adapter.clear();
            orderRef.get().addOnCompleteListener(
                    task->{
                        for (DocumentSnapshot doc: task.getResult().getDocuments()){
                            //  Log.e("AQUI", String.valueOf(task.getResult().size()));
                            Order dborder = doc.toObject(Order.class);
                            dborder.setId(doc.getId());
                        adapter.addOrder(dborder);
                    }
                }
        );
    }
}