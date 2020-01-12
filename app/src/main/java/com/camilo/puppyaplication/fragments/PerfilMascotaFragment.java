package com.camilo.puppyaplication.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.R;
import com.camilo.puppyaplication.adapters.PerfilAdapter;
import com.camilo.puppyaplication.pojo.PerfilPOJO;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilMascotaFragment extends Fragment {

    private List<PerfilPOJO> listaFotosPerfil;
    private RecyclerView reciclerViewPerfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_perfil_mascota, container, false);

        reciclerViewPerfil = view.findViewById(R.id.rv_lista_fotos_perfil);
        crearDummys();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        reciclerViewPerfil.setLayoutManager(layoutManager);
        initAdapter();

        return view;
    }

    public void initAdapter() {
        PerfilAdapter adapter = new PerfilAdapter(listaFotosPerfil);
        reciclerViewPerfil.setAdapter(adapter);
    }

    private void crearDummys() {
        listaFotosPerfil = new ArrayList<>();
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato1, 4));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato2, 47));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato3, 54));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato4, 22));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato5, 10));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato6, 14));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato7, 45));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato8, 71));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato9, 10));
        listaFotosPerfil.add(new PerfilPOJO(R.drawable.gato10, 1));
    }

}
