package com.camilo.puppyaplication.fragments;

import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.pojo.MascotaPOJO;

import java.util.List;

public interface IRecyclerViewFragmentView {
    void generarLinearLayoutVertical();

    RecyclerView.Adapter crearAdaptador(List<MascotaPOJO> listaMascotas);

    void initAdapter(RecyclerView.Adapter adapter);
}
