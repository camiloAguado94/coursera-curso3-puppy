package com.camilo.puppyaplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.R;
import com.camilo.puppyaplication.pojo.PerfilPOJO;

import java.util.ArrayList;
import java.util.List;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>{

    private List<PerfilPOJO> listaFotosPerfil;

    public PerfilAdapter(List<PerfilPOJO> listaFotosPerfil) {
        this.listaFotosPerfil = listaFotosPerfil;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_foto_pefil, parent, false);
        return new PerfilViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        PerfilPOJO foto = listaFotosPerfil.get(position);
        holder.foto.setImageResource(foto.getImagen());
        holder.cantidadLikes.setText(foto.getCantidadLikes().toString());
    }

    @Override
    public int getItemCount() {
        return listaFotosPerfil.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView cantidadLikes;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.iv_perfil_foto);
            cantidadLikes = itemView.findViewById(R.id.tv_perfil_likes);
        }
    }
}
