package com.camilo.puppyaplication.db;

import android.content.ContentValues;
import android.content.Context;

import com.camilo.puppyaplication.R;
import com.camilo.puppyaplication.pojo.MascotaPOJO;

import java.util.List;

public class ConstructorMascotas {
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public List<MascotaPOJO> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.consultarTodasMascotas();
    }

    public void insertarMascotas(BaseDatos db) {
        db.insertarMascota(crearContentValueMascota("Calvin", R.drawable.perro1, 1));
        db.insertarMascota(crearContentValueMascota("Loqui", R.drawable.perro2, 5));
        db.insertarMascota(crearContentValueMascota("Sam", R.drawable.perro3, 7));
        db.insertarMascota(crearContentValueMascota("Bony", R.drawable.perro4, 2));
        db.insertarMascota(crearContentValueMascota("Buck", R.drawable.perro5, 3));
        db.insertarMascota(crearContentValueMascota("Kevin", R.drawable.perro6, 10));
    }

    private ContentValues crearContentValueMascota(String nombre, int foto, int cantidadLikes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_NOMBRE, nombre);
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_FOTO, foto);
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES, cantidadLikes);
        return contentValues;
    }

    public void addLike(int idContacto) {
        BaseDatos db = new BaseDatos(context);
        db.addLike(idContacto);
    }

    public void removeLike(int idContacto) {
        BaseDatos db = new BaseDatos(context);
        db.removeLike(idContacto);
    }

    public List<MascotaPOJO> mascotasLiked() {
        BaseDatos db = new BaseDatos(context);
        return db.consultarMascotasLiked(5);
    }
}
