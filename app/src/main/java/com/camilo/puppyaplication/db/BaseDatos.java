package com.camilo.puppyaplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.camilo.puppyaplication.pojo.MascotaPOJO;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesDB.DB_NAME, null, ConstantesDB.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sb = new StringBuilder();

        String queryCrearTablaMascota = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
                        "%s INTEGER" +
                        ")",
                ConstantesDB.TABLE_MASCOTAS,
                ConstantesDB.TABLE_MASCOTAS_ID,
                ConstantesDB.TABLE_MASCOTAS_NOMBRE,
                ConstantesDB.TABLE_MASCOTAS_FOTO,
                ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES);

        db.execSQL(queryCrearTablaMascota);

        String queryCrearTablaMascotaLiked = String.format("CREATE TABLE %s ( " +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s INTEGER," +
                        " %s DATE" +
                        ", FOREIGN KEY ( %s ) REFERENCES %s ( %s )" +
                        " )",
                ConstantesDB.TABLE_MASCOTA_LIKED,
                ConstantesDB.TABLE_MASCOTA_LIKED_ID,
                ConstantesDB.TABLE_MASCOTA_LIKED_ID_MASCOTA,
                ConstantesDB.TABLE_MASCOTA_LIKED_FECHA_LIKED,
                ConstantesDB.TABLE_MASCOTA_LIKED_ID_MASCOTA, ConstantesDB.TABLE_MASCOTAS, ConstantesDB.TABLE_MASCOTAS_ID);
        db.execSQL(queryCrearTablaMascotaLiked);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", ConstantesDB.TABLE_MASCOTA_LIKED));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", ConstantesDB.TABLE_MASCOTAS));
        onCreate(db);
    }

    public List<MascotaPOJO> consultarTodasMascotas() {
        String query = String.format("SELECT * FROM %s", ConstantesDB.TABLE_MASCOTAS);
        SQLiteDatabase db = this.getReadableDatabase();
        List<MascotaPOJO> lista = convertirALista(db.rawQuery(query, null));
        db.close();
        return lista;
    }

    public List<MascotaPOJO> consultarMascotasLiked(Integer cantidad) {
        String query = String.format("SELECT %s.%s,%s,%s,%s " +
                        "FROM %s, %s " +
                        "WHERE %s.%s = %s.%s" +
                        " ORDER BY %s DESC " +
                        "LIMIT %s",
                ConstantesDB.TABLE_MASCOTAS, ConstantesDB.TABLE_MASCOTAS_ID, ConstantesDB.TABLE_MASCOTAS_NOMBRE, ConstantesDB.TABLE_MASCOTAS_FOTO, ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES,
                ConstantesDB.TABLE_MASCOTAS, ConstantesDB.TABLE_MASCOTA_LIKED,
                ConstantesDB.TABLE_MASCOTAS, ConstantesDB.TABLE_MASCOTA_LIKED_ID, ConstantesDB.TABLE_MASCOTA_LIKED, ConstantesDB.TABLE_MASCOTAS_ID,
                ConstantesDB.TABLE_MASCOTA_LIKED_FECHA_LIKED,
                cantidad.toString());
        SQLiteDatabase db = this.getReadableDatabase();
        List<MascotaPOJO> lista = convertirALista(db.rawQuery(query, null));
        db.close();
        return lista;
    }

    public void addLike(Integer idMascota) {
        String query1 = String.format(
                "UPDATE %s " +
                        "SET %s = %s +1 " +
                        "WHERE %s = %s ",
                ConstantesDB.TABLE_MASCOTAS,
                ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES, ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES,
                ConstantesDB.TABLE_MASCOTAS_ID, idMascota.toString());

        String query2 = String.format(
                "INSERT INTO %s (%s,%s)" +
                        "VALUES (%s,'datetime()')",
                ConstantesDB.TABLE_MASCOTA_LIKED, ConstantesDB.TABLE_MASCOTA_LIKED_ID_MASCOTA,
                ConstantesDB.TABLE_MASCOTA_LIKED_FECHA_LIKED, idMascota.toString());

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL(query1);
        db.execSQL(query2);

        db.close();
    }

    public void removeLike(Integer idMascota) {
        String query1 = String.format(
                "UPDATE %s " +
                        "SET %s = %s -1 " +
                        "WHERE %s = %s ",
                ConstantesDB.TABLE_MASCOTAS,
                ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES, ConstantesDB.TABLE_MASCOTAS_CONTIDAD_LIKES,
                ConstantesDB.TABLE_MASCOTAS_ID, idMascota.toString());

        String query2 = String.format(
                "DELETE FROM %s WHERE %s = %s",
                ConstantesDB.TABLE_MASCOTA_LIKED, ConstantesDB.TABLE_MASCOTA_LIKED_ID_MASCOTA, idMascota.toString());

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL(query1);
        db.execSQL(query2);

        db.close();
    }

    public List<MascotaPOJO> convertirALista(Cursor registros) {
        ArrayList<MascotaPOJO> listaMascotas = new ArrayList<>();
        while (registros.moveToNext()) {
            MascotaPOJO mascota = new MascotaPOJO();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));
            mascota.setCantidadLikes(registros.getInt(3));
            listaMascotas.add(mascota);
        }
        return listaMascotas;
    }

    public void insertarMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

}
