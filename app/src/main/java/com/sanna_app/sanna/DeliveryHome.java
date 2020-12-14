package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryHome extends AppCompatActivity {


    private MapsFragment mapsFragment;
    private ProfileFragment profileFragment;
    private BottomNavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_home);

    navbar = findViewById(R.id.navBarR);


    mapsFragment = MapsFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();

        showFragment(profileFragment);


        navbar.setOnNavigationItemSelectedListener(
                (menuItem) -> {
                    switch (menuItem.getItemId()) {

                        case R.id.mapView:
                            showFragment(mapsFragment);
                            break;

                        case R.id.profileView:
                            showFragment(profileFragment);
                            break;

                    }
                    return true;
                });
    
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerR,fragment);
        transaction.commit();
    }


    }

