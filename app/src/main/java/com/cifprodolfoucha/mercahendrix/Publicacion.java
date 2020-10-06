package com.cifprodolfoucha.mercahendrix;

import android.net.Uri;

public class Publicacion {
    private String nombre;
    private String precio;
    private String email;
    private Uri imagen;

    public Publicacion(String email, String nombre, String precio, Uri imagen){
        this.email = email;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getEmail() {
        return email;
    }

    public Uri getImagen() {
        return imagen;
    }
}
