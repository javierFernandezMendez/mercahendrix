package com.cifprodolfoucha.mercahendrix.almacenamiento;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.cifprodolfoucha.mercahendrix.MainActivity;
import com.cifprodolfoucha.mercahendrix.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BaseDatos_Aplicacion {
    Activity ac;
    ImageView im;

    //instancio la base de datos
    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    //creo una referencia a la raiz de mi base de datos
    private DatabaseReference ref = bd.getReference();

    public BaseDatos_Aplicacion(Activity _ac){
        this.ac = _ac;
    }

    public boolean subirImagen(Object i){
        ref.child("imagenes").push().setValue(i);
        return true;
    }

    public boolean recuperarImagen(){

        ref.child("imagenes/-MIu6bg-6-Qg1zqpN-d0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object imagen = snapshot.getValue();
                im = (ImageView) ac.findViewById(R.id.im_Foto);
                System.out.println(imagen);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return true;
    }
}
