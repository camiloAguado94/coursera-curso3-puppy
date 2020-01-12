package com.camilo.puppyaplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.pojo.MascotaPOJO;
import com.camilo.puppyaplication.R;

import java.util.List;

public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.MascotaViewHolder> {

    private List<MascotaPOJO> listaMascotas;

    public MascotasAdapter(List<MascotaPOJO> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        MascotaPOJO mascota = listaMascotas.get(position);

        holder.foto.setImageResource(mascota.getFoto());
        holder.nombre.setText(mascota.getNombre());
        holder.cantidadLikes.setText(mascota.getCantidadLikes().toString());
        if (mascota.isLiked()) {
            holder.like.setImageResource(R.drawable.bone_yellow);
        } else {
            holder.like.setImageResource(R.drawable.bone);
        }
        holder.like.setOnClickListener((v) -> {
            Integer nLikes;
            if (mascota.isLiked()) {
                nLikes = Integer.parseInt(holder.cantidadLikes.getText().toString()) - 1;
                mascota.setLiked(false);
                holder.like.setImageResource(R.drawable.bone);
            } else {
                nLikes = Integer.parseInt(holder.cantidadLikes.getText().toString()) + 1;
                mascota.setLiked(true);
                holder.like.setImageResource(R.drawable.bone_yellow);
            }
            holder.cantidadLikes.setText(nLikes.toString());
        });
    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView nombre;
        private TextView cantidadLikes;
        private ImageView like;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.iv_foto);
            nombre = itemView.findViewById(R.id.tv_nombre);
            cantidadLikes = itemView.findViewById(R.id.tv_likes);
            like = itemView.findViewById(R.id.iv_like);
        }
    }
}
