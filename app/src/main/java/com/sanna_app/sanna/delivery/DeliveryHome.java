package com.sanna_app.sanna.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.User;

public class DeliveryHome extends AppCompatActivity {


    private MapsFragment mapsFragment;
    private ProfileFragment profileFragment;
    private BottomNavigationView navbar;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_home);

        user = (User)getIntent().getExtras().get("user");
    navbar = findViewById(R.id.navBarR);


        mapsFragment = MapsFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();

        showFragment(mapsFragment);


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
        Gson gson= new Gson();

        Bundle bundle = new Bundle();
        bundle.putString("user",gson.toJson(user));
        fragment.setArguments(bundle);
    }


    }

