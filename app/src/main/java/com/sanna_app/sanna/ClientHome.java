package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClientHome extends AppCompatActivity {


    ConstraintLayout frameContainer;
    BottomNavigationView navBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);


        frameContainer = findViewById(R.id.clientHome_frameContainer);
        navBar = findViewById(R.id.clientHome_bottomNavigationView);


    }//closes onCreate method


}//closes ClientHome class