package com.sanna_app.sanna;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.sanna_app.sanna.Util.UtilDomi;
import com.sanna_app.sanna.model.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

public class addProductActivity extends AppCompatActivity {

    private TextView name,price,description;
    private ImageView photo;
    private Button addButton;
    private  static final int GALLERY_CALLBACK=-1;
    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        }, 1);
        mAuth=FirebaseAuth.getInstance();
        storage= FirebaseStorage.getInstance();
        db= FirebaseFirestore.getInstance();

        name=findViewById(R.id.NameProductInventoryET);
        description=findViewById(R.id.DescriptionProdInvET);
        price=findViewById(R.id.ValorProdInventarioET);
        photo=findViewById(R.id.addProductInventoryImg);

        addButton= findViewById(R.id.CreateProductButton);

        photo.setOnClickListener(
                v->{
                    Intent i=new Intent();
                    i.setType("/image/*");
                    startActivityForResult(i,GALLERY_CALLBACK);
                }
        );

        addButton.setOnClickListener(
                e->{
                    if(name.getText()!="" && description.getText().length()>0 && price.getText().length()>0){
                        try{
                            Product np=new Product();
                            String id= UUID.randomUUID().toString();
                            FileInputStream is=new FileInputStream(new File(path));

                            storage.getReference().child("products").child(id).putStream(is).addOnCompleteListener(
                                    t->{
                                        if(t.isSuccessful()) {
                                            np.setId(id);
                                            np.setName(name.getText().toString());
                                            np.setDescription(description.getText().toString());
                                            np.setPhoto(id);
                                            db.collection("products").document(id).set(np);
                                            finish();
                                        }
                                    }
                            );
                        }catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } {

                        }
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_CALLBACK && resultCode==RESULT_OK){
            Uri pUri=data.getData();
            path= UtilDomi.getPath(this,pUri);
            Bitmap bm= BitmapFactory.decodeFile(path);
            photo.setImageBitmap(bm);
        }
    }
}