package com.cifprodolfoucha.mercahendrix;

import android.net.Uri;

import java.text.DateFormat;
import java.util.Date;

public class Publicacion {
    private String nombre;
    private String precio;
    private String email;
    private String imagen;
    //private String currentDateTimeString;

    public Publicacion() {

    }

    public Publicacion(String email, String nombre, String precio, String imagen){
        this.email = email;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        //currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    //public String getHora(){
    //    return currentDateTimeString;
    //}
}
