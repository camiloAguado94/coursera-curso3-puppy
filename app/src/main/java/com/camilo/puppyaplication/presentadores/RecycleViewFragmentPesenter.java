package com.camilo.puppyaplication.presentadores;

import android.content.Context;

import com.camilo.puppyaplication.db.ConstructorMascotas;
import com.camilo.puppyaplication.fragments.IRecyclerViewFragmentView;
import com.camilo.puppyaplication.pojo.MascotaPOJO;

import java.util.List;

public class RecycleViewFragmentPesenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView fragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private List<MascotaPOJO> listaMascotas;

    public RecycleViewFragmentPesenter(IRecyclerViewFragmentView fragmentView, Context context) {
        this.fragmentView = fragmentView;
        this.context = context;
        obtenerMascotas();
    }

    @Override
    public void obtenerMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        listaMascotas = constructorMascotas.obtenerDatos();
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        fragmentView.initAdapter(fragmentView.crearAdaptador(listaMascotas));
        fragmentView.generarLinearLayoutVertical();
    }
}
