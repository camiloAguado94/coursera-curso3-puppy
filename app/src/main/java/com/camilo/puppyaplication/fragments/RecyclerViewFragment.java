package com.camilo.puppyaplication.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.R;
import com.camilo.puppyaplication.adapters.MascotasAdapter;
import com.camilo.puppyaplication.pojo.MascotaPOJO;
import com.camilo.puppyaplication.presentadores.IRecyclerViewFragmentPresenter;
import com.camilo.puppyaplication.presentadores.RecycleViewFragmentPesenter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    private RecyclerView reciclerViewMascotas;
    public static List<MascotaPOJO> listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        reciclerViewMascotas = view.findViewById(R.id.rv_lista_mascotas);
        presenter = new RecycleViewFragmentPesenter(this, getContext());
        return view;
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerViewMascotas.setLayoutManager(linearLayoutManager);
    }

    @Override
    public MascotasAdapter crearAdaptador(List<MascotaPOJO> listaMascotas) {
        return new MascotasAdapter(listaMascotas);
    }

    @Override
    public void initAdapter(RecyclerView.Adapter adapter) {
        reciclerViewMascotas.setAdapter(adapter);
    }
}
