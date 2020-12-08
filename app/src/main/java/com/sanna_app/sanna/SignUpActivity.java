package com.sanna_app.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanna_app.sanna.constants.Constants;
import com.sanna_app.sanna.model.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nameET, emailET, addressET, passwordET, repasswordET;
    private RadioGroup rg;
    private RadioButton roleRB;
    private Button signUpBtn;
    private TextView loginLink;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailETSU);
        addressET = findViewById(R.id.addressET);
        passwordET = findViewById(R.id.passwordETSU);
        repasswordET = findViewById(R.id.repasswordETSU);
        signUpBtn = findViewById(R.id.signUpBtnSU);
        loginLink = findViewById(R.id.loginBtnSU);
        rg = findViewById(R.id.rolesRG);

        loginLink.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtnSU:
                finish();
                break;
            case R.id.signUpBtnSU:
                auth.createUserWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString()).addOnCompleteListener(
                        task -> {
                            if(task.isSuccessful()){
                                FirebaseUser fbuser = auth.getCurrentUser();
                                if(rg.getCheckedRadioButtonId() != -1){
                                    roleRB = findViewById(rg.getCheckedRadioButtonId());
                                    String role = roleRB.getText().toString();
                                    int roleValue = getRoleValue(role);
                                    User user = new User(
                                            fbuser.getUid(),
                                            nameET.getText().toString(),
                                            emailET.getText().toString(),
                                            addressET.getText().toString(),
                                            roleValue
                                    );
                                    db.collection("users").document(user.getId()).set(user).addOnCompleteListener(
                                            dbtask -> {
                                                if(dbtask.isSuccessful()) sendVerification();
                                            }
                                    );
                                }
                            }else{
                                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                break;
        }
    }

    private int getRoleValue(String role) {
        switch (role){
            case "Cliente":
                return Constants.CLIENT_ROLE;
            case "Proveedor":
                return Constants.PROVIDER_ROLE;
            default:
                return Constants.DELIVERY_ROLE;
        }
    }


    private void sendVerification() {
        auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(this, "Email de verificación enviado, revise su bandeja de entrada", Toast.LENGTH_LONG).show();
                        auth.signOut();
                    }else{
                        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}