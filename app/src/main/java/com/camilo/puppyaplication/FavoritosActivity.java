package com.camilo.puppyaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.adapters.MascotasFavoritasAdapter;
import com.camilo.puppyaplication.fragments.IRecyclerViewFragmentView;
import com.camilo.puppyaplication.pojo.MascotaPOJO;
import com.camilo.puppyaplication.presentadores.IRecyclerViewFragmentPresenter;
import com.camilo.puppyaplication.presentadores.RecyclerViewFavoritosPresenter;

import java.util.List;

public class FavoritosActivity extends AppCompatActivity implements IRecyclerViewFragmentView {

    List<MascotaPOJO> listaMascotas;
    private RecyclerView reciclerViewMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar actionBar = findViewById(R.id.mi_action_bar);

        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setReciclerViewMascotas();

        if (actionBar != null) {
            setSupportActionBar(actionBar);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setReciclerViewMascotas() {
        reciclerViewMascotas = findViewById(R.id.rv_favoritos);
        presenter = new RecyclerViewFavoritosPresenter(this, this);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerViewMascotas.setLayoutManager(linearLayoutManager);
    }

    @Override
    public MascotasFavoritasAdapter crearAdaptador(List<MascotaPOJO> listaMascotas) {
        return new MascotasFavoritasAdapter(listaMascotas);
    }

    @Override
    public void initAdapter(RecyclerView.Adapter adapter) {
        reciclerViewMascotas.setAdapter(adapter);
    }

}
