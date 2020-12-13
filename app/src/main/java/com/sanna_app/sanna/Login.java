package com.sanna_app.sanna;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanna_app.sanna.constants.Constants;
import com.sanna_app.sanna.model.User;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static int LOGIN=-1;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private EditText emailET;
    private EditText passwordET;
    private Button loginBtn;
    private Button signUpBtn;
    private FirebaseFirestore db;
    private SignInButton google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google = findViewById(R.id.GLogIn);
        google.setSize(SignInButton.SIZE_STANDARD);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if (mAuth.getCurrentUser() != null) ResumeSession();

        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        loginBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        google.setOnClickListener(this);
    }

    private void ResumeSession() {

        goToHomeScreen(mAuth.getCurrentUser());

    }//closes ResumeSession methos


    public void goToHomeScreen(FirebaseUser user) {
        db.collection("users").document(user.getUid()).get().addOnCompleteListener(
                task -> {
                    DocumentSnapshot snapshot=task.getResult();
                    User u = snapshot.toObject(User.class);
                    /**for(DocumentSnapshot doc : task.getResult()){
                        u = doc.toObject(User.class);
                    }*/
                    sortRole(u.getRole());
                });
    }

    private void sortRole(int u) {
        if(u != 0) {
            Intent i;
            switch (u){
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String email = emailET.getText().toString(), password = passwordET.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                                task -> {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(">>>", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user.isEmailVerified()) {
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

            case R.id.GLogIn:
                gLogIn();
                break;
        }
    }

    private void gLogIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==LOGIN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try{
            GoogleSignInAccount account= task.getResult();
            db.collection("users").document(account.getId()).get().addOnCompleteListener(
                    t->{
                        if(t.isSuccessful()){
                            DocumentSnapshot doc=t.getResult();
                            User u=doc.toObject(User.class);
                            sortRole(u.getRole());
                        }else{
                            User newU=new User();
                            newU.setId(account.getId());
                            newU.setName(account.getDisplayName());
                            newU.setEmail(account.getEmail());
                            newU.setAddress("");
                            newU.setRole(Constants.CLIENT_ROLE);
                            db.collection("users").document(newU.getId()).set(newU).addOnCompleteListener(
                                    task2->{
                                        if(task2.isSuccessful()){
                                            sortRole(newU.getRole());
                                        }
                                    }
                            );
                        }


                    }
            );
        }catch (Exception e){
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}