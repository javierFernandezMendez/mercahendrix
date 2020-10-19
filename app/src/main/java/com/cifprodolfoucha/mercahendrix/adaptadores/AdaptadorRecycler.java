package com.cifprodolfoucha.mercahendrix.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cifprodolfoucha.mercahendrix.Publicacion;
import com.cifprodolfoucha.mercahendrix.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MiViewHolder> {
    private Context context;
    private List<Publicacion> listaPublicaciones;


    public class MiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCorreo, tvPrecio;
        public ImageView imPublicacion;
        public MiViewHolder(@NonNull View vista){
            super(vista);
            imPublicacion = (ImageView) vista.findViewById(R.id.postear_imagen);
            tvCorreo = (TextView) vista.findViewById(R.id.tv_correo);
            tvPrecio = (TextView) vista.findViewById(R.id.tv_precio);
        }
    }

    public AdaptadorRecycler(Context context, List<Publicacion> listaPublicaciones){
        this.context = context;
        this.listaPublicaciones = listaPublicaciones;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(context).inflate(R.layout.layout_publicacion, null, false);
        return new MiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.tvCorreo.setText(listaPublicaciones.get(position).getEmail());
        holder.tvPrecio.setText(listaPublicaciones.get(position).getPrecio());
        Glide.with(context).load(listaPublicaciones.get(position).getImagen()).into(holder.imPublicacion);
    }

    @Override
    public int getItemCount() {
        return listaPublicaciones.size();
    }


}
