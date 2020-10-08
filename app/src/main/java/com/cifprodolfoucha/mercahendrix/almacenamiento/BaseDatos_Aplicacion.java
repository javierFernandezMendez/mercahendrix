package com.cifprodolfoucha.mercahendrix.almacenamiento;

import android.app.Activity;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.cifprodolfoucha.mercahendrix.Activity_ListaProductos;
import com.cifprodolfoucha.mercahendrix.Publicacion;
import com.cifprodolfoucha.mercahendrix.R;
import com.google.android.gms.tasks.Task;
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
import java.util.Objects;

public class BaseDatos_Aplicacion {
    Activity ac;
    Activity_ListaProductos listaProductos1 = new Activity_ListaProductos();

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

    public void subirPublicacion(Publicacion p){

        String email = p.getEmail().replace(".","");
        String key = bdRef.child("publicaciones/" + email).push().getKey();
        //subo la imagen
        UploadTask procesoSubida = storageRef.child(email + "/" + key).putFile(Uri.parse(p.getImagen()));
        //guardo la url de la imagen
        //p.setImagen("gs://mercahendrix.appspot.com/"+email+"/"+key);
        p.setImagen(storageRef.child(email+"/"+key).getDownloadUrl().toString());
        //subo la publicacion a la base de datos
        bdRef.child("publicaciones/"+email+"/"+key).setValue(p);


    }

    public void recuperarPublicacion(){
        Publicacion p;
        bdRef.child("publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //recorro correos
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //recorro publicaciones
                    for (DataSnapshot snapshot2 : snapshot1.getChildren()){
                        listaProductos1.crearPublicacion(snapshot2.getValue(Publicacion.class));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
