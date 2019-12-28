package com.camilo.puppyaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camilo.puppyaplication.Adapters.MascotasAdapter;
import com.camilo.puppyaplication.POJOs.MascotaPOJO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    List<MascotaPOJO> listaMascotas;
    private RecyclerView reciclerViewMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar actionBar = findViewById(R.id.mi_action_bar);

        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        try {

            Gson gson = new Gson();
            listaMascotas = gson.fromJson(bundle.getString("listaMascotas"), new TypeToken<List<MascotaPOJO>>() {
            }.getType());
            reciclerViewMascotas = findViewById(R.id.rv_favoritos);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            reciclerViewMascotas.setLayoutManager(linearLayoutManager);
            MascotasAdapter adapter = new MascotasAdapter(listaMascotas);
            reciclerViewMascotas.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
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
}
