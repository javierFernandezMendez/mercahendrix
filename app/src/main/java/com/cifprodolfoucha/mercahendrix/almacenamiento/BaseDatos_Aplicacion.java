package com.cifprodolfoucha.mercahendrix.almacenamiento;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cifprodolfoucha.mercahendrix.Activity_PantallaPrincipal;
import com.cifprodolfoucha.mercahendrix.Publicacion;
import com.cifprodolfoucha.mercahendrix.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos_Aplicacion {
    Activity ac;
    static public List<Publicacion> pub = new ArrayList();
    //realtime database
    //instancio la base de datos
    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    //creo una referencia a la raiz de mi base de datos
    private DatabaseReference bdRef = bd.getReference();

    //storage
    //instacia storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    //referencia a la raiz de mi almacenamiento
    StorageReference storageRef = storage.getReference("/");
    StorageReference urlStorageRef = storage.getReferenceFromUrl("gs://mercahendrix.appspot.com");

    public BaseDatos_Aplicacion(Activity _ac){
        this.ac = _ac;
    }

    public void subirPublicacion(final Publicacion p){

        //remplazo el . del email por nada para poder usarlo como ruta en mi base de datos
        final String email = p.getEmail().replace(".","");
        //creo y guardo una clave unica de publicacion
        final String key = bdRef.child("publicaciones/" + email).push().getKey();
        //subo la imagen con un path personalizado
        UploadTask procesoSubida = storageRef.child(email + "/" + key).putFile(Uri.parse(p.getImagen()));
        //cuando se complete la subida
        procesoSubida.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> tarea) {
                //si se termino la subida
                if (tarea.isSuccessful()){
                    tarea.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            p.setImagen(uri.toString());
                            Activity_PantallaPrincipal.amosarMensaxeDebug("url descarca: " + p.getImagen());
                            bdRef.child("publicaciones/"+email+"/"+key).setValue(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ac, "Publicación subida.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ac, "Error subiendo la publicación.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ac, "Error al recuperar la imagen almacenada.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    String message = tarea.getException().getMessage();
                    Toast.makeText(ac, "Error al subir la imagen.", Toast.LENGTH_SHORT).show();
                    Activity_PantallaPrincipal.amosarMensaxeDebug("error subida imagen: " + message);
                }
            }
        });
    }

    public void recuperarPublicacion(){

        bdRef.child("publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pub.clear();
                //recorro correos
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //recorro publicaciones
                    for (DataSnapshot snapshot2 : snapshot1.getChildren()){
                        //Activity_PantallaPrincipal.amosarMensaxeDebug("publicacion.imagen: " + snapshot2.getValue(Publicacion.class).getImagen());
                        //Glide.with(ac).load(snapshot2.getValue(Publicacion.class).getImagen()).override(400, 400).into(im);
                        pub.add(snapshot2.getValue(Publicacion.class));
                        Activity_PantallaPrincipal.amosarMensaxeDebug("size pub dentro: " + pub.size());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Activity_PantallaPrincipal.amosarMensaxeDebug("error recuperar publicaciones: " + error.getMessage());
                Toast.makeText(ac, "Error al recuperar las publicaciones.", Toast.LENGTH_SHORT).show();
            }
        });
        Activity_PantallaPrincipal.amosarMensaxeDebug("size pub: " + pub.size());
    }

}
