package com.sanna_app.sanna.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.User;

public class ConfigProfile extends AppCompatActivity {

    private EditText name, email, direccion;
    private User user;
    private String nombre, correo, direc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_profile);


        name = findViewById(R.id.nameRepartidor);
        email = findViewById(R.id.emailED);
        direccion = findViewById(R.id.direccionED);



        if (user!=null){
            nombre = user.getName();
            correo = user.getEmail();
            direc = user.getAddress();

            name.setHint(nombre);
            email.setHint(correo);
            direccion.setHint(direc);
        }




    }
}