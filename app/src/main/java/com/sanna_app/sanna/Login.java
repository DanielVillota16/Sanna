package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanna_app.sanna.constants.Constants;
import com.sanna_app.sanna.model.User;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText emailET;
    private EditText passwordET;
    private Button loginBtn;
    private Button signUpBtn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        db = FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    public void goToHomeScreen(FirebaseUser user) {
        db.collection("users").whereEqualTo("id", user.getUid()).get().addOnCompleteListener(
                task -> {
                    for(DocumentSnapshot doc : task.getResult()){
                        User u = doc.toObject(User.class);
                        if(u != null) {
                            Intent i;
                            switch (u.getRole()){
                                case Constants.CLIENT_ROLE:
                                    i = new Intent(this, ClientHome.class);
                                    break;
                                case Constants.PROVIDER_ROLE:
                                    i = new Intent(this, ProviderHome.class);
                                    break;
                                default:
                                    i = new Intent(this, DeliveryHome.class);
                                    break;
                            }
                            startActivity(i);
                            this.finish();
                        } else
                            Toast.makeText(this, "No se encontró el usuario!", Toast.LENGTH_LONG).show();
                        break;
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                String email = emailET.getText().toString(), password = passwordET.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                                task -> {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(">>>", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user.isEmailVerified()){
                                            goToHomeScreen(user);
                                        } else {
                                            Toast.makeText(this, "Debe verificar su cuenta antes de ingresar", Toast.LENGTH_LONG).show();
                                            mAuth.signOut();
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(">>>", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                break;
            case R.id.signUpBtn:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}