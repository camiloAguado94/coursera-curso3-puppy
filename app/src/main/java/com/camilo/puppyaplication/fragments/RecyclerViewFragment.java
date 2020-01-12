package com.camilo.puppyaplication.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camilo.puppyaplication.R;
import com.camilo.puppyaplication.adapters.MascotasAdapter;
import com.camilo.puppyaplication.pojo.MascotaPOJO;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView reciclerViewMascotas;
    public static List<MascotaPOJO> listaMascotas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        reciclerViewMascotas = view.findViewById(R.id.rv_lista_mascotas);
        crearDummys();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerViewMascotas.setLayoutManager(linearLayoutManager);
        initAdapter();

        return view;
    }

    public void initAdapter() {
        MascotasAdapter adapter = new MascotasAdapter(listaMascotas);
        reciclerViewMascotas.setAdapter(adapter);
    }

    private void crearDummys() {
        listaMascotas = new ArrayList<>();
        listaMascotas.add(new MascotaPOJO("Calvin", R.drawable.perro1, 1));
        listaMascotas.add(new MascotaPOJO("Loqui", R.drawable.perro2, 5));
        listaMascotas.add(new MascotaPOJO("Sam", R.drawable.perro3, 7));
        listaMascotas.add(new MascotaPOJO("Bony", R.drawable.perro4, 2));
        listaMascotas.add(new MascotaPOJO("Buck", R.drawable.perro5, 3));
    }

}
