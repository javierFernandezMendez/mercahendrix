package com.cifprodolfoucha.mercahendrix.almacenamiento;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cifprodolfoucha.mercahendrix.Activity_PantallaPrincipal;
import com.cifprodolfoucha.mercahendrix.Publicacion;
import com.cifprodolfoucha.mercahendrix.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class BaseDatos_Aplicacion {
    Activity ac;
    Activity_PantallaPrincipal main = new Activity_PantallaPrincipal();
    ImageView im;

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

    public BaseDatos_Aplicacion(Activity _ac){
        this.ac = _ac;
        im = (ImageView) ac.findViewById(R.id.im_Foto);
    }

    public void subirPublicacion(Publicacion p){

        //remplazo el . del email por nada para poder usarlo como ruta en mi base de datos
        String email = p.getEmail().replace(".","");
        //creo y guardo una clave unica de publicacion
        String key = bdRef.child("publicaciones/" + email).push().getKey();
        //subo la imagen
        UploadTask procesoSubida = storageRef.child(email + "/" + key).putFile(Uri.parse(p.getImagen()));
        Activity_PantallaPrincipal.amosarMensaxeDebug("url imagen: " + storageRef.child("publicaciones/"+email+"/"+key).getDownloadUrl().toString());

        //p.setImagen("gs://mercahendrix.appspot.com/"+email+"/"+key);
        storageRef.child(email+"/"+key).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Activity_PantallaPrincipal.amosarMensaxeDebug("uri: " + uri);
            }
        });
        p.setImagen(storageRef.child(email+"/"+key).getDownloadUrl().toString());
        //subo la publicacion a la base de datos
        bdRef.child("publicaciones/"+email+"/"+key).setValue(p);


    }

    public void recuperarPublicacion(){
        Publicacion p;
        Glide.with(ac).load(storageRef.child("dora.jpg")).into(im);
        bdRef.child("publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //recorro correos
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //recorro publicaciones
                    for (DataSnapshot snapshot2 : snapshot1.getChildren()){
                        Activity_PantallaPrincipal.amosarMensaxeDebug("publicacion.imagen: " + snapshot2.getValue(Publicacion.class).getImagen());
                        //Glide.with(ac).load(snapshot2.getValue(Publicacion.class).getImagen()).override(400, 400).into(im);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
