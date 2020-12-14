package com.sanna_app.sanna;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.LatLng;
import com.sanna_app.sanna.model.Order;
import com.sanna_app.sanna.model.Product;

import java.util.UUID;

public class Dummy {

    private LatLng loc;
    private FirebaseFirestore db;

    public Dummy(){
        db=FirebaseFirestore.getInstance();
        Order o= new Order();
        o.setId(UUID.randomUUID().toString());
        o.setProviderId("jbUQ0Gh5oEX9N95zydPp3AGHwK12");
        o.setDestName("Zuñiga");
        //loc= LatLng.newBuilder().getDefaultInstanceForType();
        //o.setAddressFrom(loc);
        //o.setAddressTo(loc);
        db.collection("orders").document(o.getId()).set(o);

        DocumentReference orderRef = db.collection("products").document("f3b29ad4-7c74-4878-9d92-b075730f3a53");
        orderRef.get().addOnCompleteListener(
                v->{
                    if(v.isSuccessful()){
                        DocumentSnapshot doc=v.getResult();
                        Product p=doc.toObject(Product.class);
                        p.setQuantity(10);
                        db.collection("orders").document(o.getId()).collection("products").document(p.getId()).set(p);
                    }
                }
        );
    }
}
