package com.camilo.puppyaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.Adapters.MascotasAdapter;
import com.camilo.puppyaplication.POJOs.MascotaPOJO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView reciclerViewMascotas;
    private List<MascotaPOJO> listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = findViewById(R.id.mi_action_bar);
        setSupportActionBar(actionBar);

        reciclerViewMascotas = findViewById(R.id.rv_lista_mascotas);
        crearDummys();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerViewMascotas.setLayoutManager(linearLayoutManager);
        initAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view:
                Intent intent = new Intent(this, FavoritosActivity.class);

                Gson gson = new Gson();
                intent.putExtra("listaMascotas", gson.toJson(listaMascotas));
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
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
