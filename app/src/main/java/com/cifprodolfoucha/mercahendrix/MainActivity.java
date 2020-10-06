package com.cifprodolfoucha.mercahendrix;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cifprodolfoucha.mercahendrix.almacenamiento.BaseDatos_Aplicacion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_subirImagen;
    Button btn_subirPublicacion;
    Button btn_feed;
    ImageView im;
    Uri uri;
    BaseDatos_Aplicacion bd;

    //codigo de identificacion de la activity para recuperar imagen
    private static final int IMAGEN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BaseDatos_Aplicacion(this);

        btn_subirImagen = (Button) findViewById(R.id.btn_subirImagen);
        btn_subirPublicacion = (Button) findViewById(R.id.btn_SubirPublicacion);
        btn_feed = (Button) findViewById(R.id.btn_feed);
        im = (ImageView) findViewById(R.id.im_Foto);

        btn_subirImagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buscarArchivo();

            }
        });

        btn_subirPublicacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                crearPublicacion();
            }
        });

        final Intent activity_listaProductos = new Intent(this, Activity_ListaProductos.class);

        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activity_listaProductos);
            }
        });
    }

    public void crearPublicacion(){
        bd.subirPublicacion(new Publicacion("j@gmail.com", "Javier Fernandez", "2€", uri.toString()));
    }


    public void buscarArchivo() {

        // ACTION_OPEN_DOCUMENT es el intent que abre el filebrowser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filtro que solo muestra archivos que se puedan abrir.
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filtro para mostrar solo imagenes, usando el tipo de dato MIME.
        intent.setType("image/*");

        startActivityForResult(intent, IMAGEN_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);

        //si el requestCode coincide con el designado anteriormente y la actividad finalizo correctamente
        if (requestCode == IMAGEN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            //recupero la imagen con su uri

            Bitmap bitmap = null;
            if (resultData != null) {
                uri = resultData.getData();
                im.setImageURI(uri);

            }
        }
    }

}