package com.sanna_app.sanna.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanna_app.sanna.Login;
import com.sanna_app.sanna.R;
import com.sanna_app.sanna.model.User;

public class ConfigProfile extends AppCompatActivity {

    private EditText name, email, direccion;
    private User user1;
    private String nombre, correo, direc;
    private Button guardar, cerrar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_profile);


        name = findViewById(R.id.nameRepartidor);
        email = findViewById(R.id.emailED);
        direccion = findViewById(R.id.direccionED);
        guardar = findViewById(R.id.btnGuardar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user1 = (User) getIntent().getExtras().get("user");
        FirebaseUser user;
        user = mAuth.getCurrentUser();

                    nombre = user1.getName();
                    correo = user1.getEmail();
                    direc = user1.getAddress();

                    if (user1!=null){


                        name.setHint(nombre);
                        email.setHint(correo);
                        direccion.setHint(direc);
                    }



            guardar.setOnClickListener(
                 (v)->{

                     nombre =  name.getText().toString();
                     correo =  email.getText().toString();

                     direc = direccion.getText().toString();

                        editUserDatabase();

                    }
                );




    }

    private void editUserDatabase() {
        user1.setName(nombre);

        user1.setEmail(correo);
        user1.setAddress(direc);

        db.collection("users").document(user1.getId()).set(user1);
        Toast.makeText(this, "Se ha efectuado los cambios realizados",Toast.LENGTH_LONG).show();

    }


}