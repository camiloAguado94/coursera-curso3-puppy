package com.camilo.puppyaplication.presentadores;

import android.content.Context;

import com.camilo.puppyaplication.db.ConstructorMascotas;
import com.camilo.puppyaplication.fragments.IRecyclerViewFragmentView;
import com.camilo.puppyaplication.pojo.MascotaPOJO;

import java.util.List;

public class RecyclerViewFavoritosPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView view;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private List<MascotaPOJO> listaMascotas;

    public RecyclerViewFavoritosPresenter(IRecyclerViewFragmentView view, Context context) {
        this.view = view;
        this.context = context;
        obtenerMascotas();
    }

    @Override
    public void obtenerMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        listaMascotas = constructorMascotas.mascotasLiked();
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        view.initAdapter(view.crearAdaptador(listaMascotas));
        view.generarLinearLayoutVertical();
    }
}
