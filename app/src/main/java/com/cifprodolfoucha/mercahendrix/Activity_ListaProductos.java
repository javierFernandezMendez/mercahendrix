package com.cifprodolfoucha.mercahendrix;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cifprodolfoucha.mercahendrix.adaptadores.AdaptadorRecycler;
import com.cifprodolfoucha.mercahendrix.almacenamiento.BaseDatos_Aplicacion;

public class Activity_ListaProductos extends AppCompatActivity {
    BaseDatos_Aplicacion bd;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_productos);

        bd = new BaseDatos_Aplicacion(this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new AdaptadorRecycler(datos);
        recyclerView.setAdapter(mAdapter);

    }

}