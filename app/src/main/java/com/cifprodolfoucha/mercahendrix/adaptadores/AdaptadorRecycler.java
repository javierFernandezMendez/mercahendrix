package com.cifprodolfoucha.mercahendrix.adaptadores;

import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cifprodolfoucha.mercahendrix.Publicacion;
import com.cifprodolfoucha.mercahendrix.R;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MiViewHolder> {
    private Publicacion[] dataSet;
    private ImageView imPublicacion;
    private TextView tvCorreo, tvPrecio;

    public class MiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCorreo, tvPrecio;
        public MiViewHolder(@NonNull View vista){
            super(vista);
            imPublicacion = (ImageView) vista.findViewById(R.id.postear_imagen);
            tvCorreo = (TextView) vista.findViewById(R.id.tv_correo);
            tvPrecio = (TextView) vista.findViewById(R.id.tv_precio);
        }
    }

    public AdaptadorRecycler(Publicacion[] dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public AdaptadorRecycler.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
