package com.cifprodolfoucha.mercahendrix;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cifprodolfoucha.mercahendrix.adaptadores.AdaptadorRecycler;
import com.cifprodolfoucha.mercahendrix.almacenamiento.BaseDatos_Aplicacion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Activity_ListaProductos extends AppCompatActivity {
    BaseDatos_Aplicacion bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_productos);
        bd = new BaseDatos_Aplicacion(this);
        bd.recuperarPublicacion();
        //Activity_PantallaPrincipal.amosarMensaxeDebug("size lista publicaciones: " + lista.size());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AdaptadorRecycler miAdaptador = new AdaptadorRecycler(this, BaseDatos_Aplicacion.pub);
        recyclerView.setAdapter(miAdaptador);

    }
}