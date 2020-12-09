package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.sanna_app.sanna.model.User;

public class ProviderHome extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private User u;
    private FirebaseFirestore db;
    private Button inventory,orders,logoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);
        inventory=findViewById(R.id.ToInventoryBtn);
        orders=findViewById(R.id.ToProviderOrderBtn);
        logoff=findViewById(R.id.LogOutProviderBtn);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        resolveUser();
        inventory.setOnClickListener(this);
        orders.setOnClickListener(this);
        logoff.setOnClickListener(this);

    }

    private void resolveUser() {
        FirebaseUser fUser=mAuth.getCurrentUser();
        db.collection("users").document(fUser.getUid()).get().addOnCompleteListener(
                t->{
                    if(t.isSuccessful()){
                        DocumentSnapshot snapshot= t.getResult();
                        u=snapshot.toObject(User.class);
                        Toast.makeText(this,"Bienvenido: "+u.getName(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ToInventoryBtn:
                Intent i= new Intent(this, InventoryMenu.class);
                startActivity(i);
                break;

            case  R.id.ToProviderOrderBtn:
                Intent j= new Intent(this, OrderMenu.class);
                startActivity(j);
                break;

            case R.id.LogOutProviderBtn:
                mAuth.signOut();
                finish();
        }

    }
}