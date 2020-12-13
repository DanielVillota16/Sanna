package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sanna_app.sanna.client.ClientCatalogue;
import com.sanna_app.sanna.client.ClientOrders;

public class ClientHome extends AppCompatActivity {


    ConstraintLayout frameContainer;
    BottomNavigationView navBar;

    ClientCatalogue catalogue = ClientCatalogue.newInstance();
    ClientOrders orders = ClientOrders.newInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        frameContainer = findViewById(R.id.clientHome_frameContainer);
        navBar = findViewById(R.id.clientHome_bottomNavigationView);


        navBar.setOnNavigationItemSelectedListener((v) -> {

            switch (v.getItemId()) {

                case R.id.client_navBar_catalogue:
                    showFragment(catalogue);
                    break;

                case R.id.client_navBar_orders:
                    showFragment(orders);
                    break;

            }
            return true;
        });


        navBar.setSelectedItemId(R.id.client_navBar_orders);
        showFragment(orders);


    }//closes onCreate method


    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameContainer.getId(), fragment);
        transaction.commit();

    }//closes showFragment method


}//closes ClientHome class