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

    BaseDatos_Aplicacion bd;

    //codigo de identificacion de la activity para recuperar imagen
    private static final int IMAGEN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BaseDatos_Aplicacion(this);
        bd.recuperarImagen();

        btn_subirImagen = (Button) findViewById(R.id.btn_subirImagen);

        btn_subirImagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buscarArchivo();

            }
        });
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
            Uri uri = null;
            Bitmap bitmap = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("uri: "+uri);
                bd.subirImagen(bitmap);

            }
        }
    }

}