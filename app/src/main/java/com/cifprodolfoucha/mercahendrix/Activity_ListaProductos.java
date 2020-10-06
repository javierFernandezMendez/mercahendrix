package com.cifprodolfoucha.mercahendrix;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.cifprodolfoucha.mercahendrix.almacenamiento.BaseDatos_Aplicacion;

public class Activity_ListaProductos extends AppCompatActivity {
    BaseDatos_Aplicacion bd;
    ImageView im_cuadroImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_productos);

        bd = new BaseDatos_Aplicacion(this);

        recuperarProductos();
    }

    public void recuperarProductos(){
        bd.recuperarPublicacion();
    }

    public void crearPublicacion(Publicacion p){
        //im_cuadroImagen = (ImageView) findViewById(R.id.im_cuadroImagen);
        ImageView imageView = new ImageView(this);
        imageView.setImageURI(Uri.parse(p.getImagen()));
    }

}