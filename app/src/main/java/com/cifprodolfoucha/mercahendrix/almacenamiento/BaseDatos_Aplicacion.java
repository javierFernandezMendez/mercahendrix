package com.cifprodolfoucha.mercahendrix.almacenamiento;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.cifprodolfoucha.mercahendrix.Publicacion;
import com.cifprodolfoucha.mercahendrix.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class BaseDatos_Aplicacion {
    Activity ac;


    //realtime database
    //instancio la base de datos
    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    //creo una referencia a la raiz de mi base de datos
    private DatabaseReference bdRef = bd.getReference();

    //storage
    //instacia storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    //referencia a la raiz de mi almacenamiento
    StorageReference storageRef = storage.getReference();

    public BaseDatos_Aplicacion(Activity _ac){
        this.ac = _ac;
    }

    public void subirImagen(Uri i){
        UploadTask procesoSubida = storageRef.child("imagenes/imagen").putFile(i);


    }

    public void subirPublicacion(Publicacion p){
        Map mapa = new HashMap<String, String>();
        mapa.put("Nombre", p.getNombre());
        mapa.put("Precio", p.getPrecio());
        String email = p.getEmail().replace(".","");
        String key = bdRef.child("publicaciones/" + email).push().getKey();
        bdRef.child("publicaciones/"+email+"/"+key).setValue(mapa);
        UploadTask procesoSubida = storageRef.child(email + "/" + key).putFile(p.getImagen());

    }

}
