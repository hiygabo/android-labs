package com.example.app8crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Databasehelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gimnasio.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RUTINAS = "rutinas";
    private static final String COL_RUTINA_ID = "id";
    private static final String COL_RUTINA_NOMBRE = "nombre_rutina";


    private static final String TABLE_EJERCICIOS = "ejercicios";
    private static final String COL_EJER_ID = "id";
    private static final String COL_EJER_NOMBRE = "nombre_ejercicio";
    private static final String COL_EJER_SERIES = "series";
    private static final String COL_EJER_RUTINA_ID ="rutina_id";



    private static final String CREATE_TABLE_RUTINAS = "CREATE TABLE " + TABLE_RUTINAS + "("
            + COL_RUTINA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_RUTINA_NOMBRE + " TEXT NOT NULL"
            + ");";
    private static final String CREATE_TABLE_EJERCICIOS = "CREATE TABLE " + TABLE_EJERCICIOS + "("
            + COL_EJER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_EJER_NOMBRE + " TEXT NOT NULL, "
            + COL_EJER_SERIES + " TEXT NOT NULL, "
            + COL_EJER_RUTINA_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + COL_EJER_RUTINA_ID + ") REFERENCES " + TABLE_RUTINAS + "(" + COL_RUTINA_ID + ") ON DELETE CASCADE"
            + ");";

    public Databasehelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_RUTINAS);
        db.execSQL(CREATE_TABLE_EJERCICIOS);
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EJERCICIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUTINAS);
        onCreate(db);
    }

    public long insertarRutina(Rutina rutina) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_RUTINA_NOMBRE, rutina.getNombreRutina());
        long id = db.insert(TABLE_RUTINAS, null, values);
        return id;
    }

    public long insertarEjercicio(Ejercicio ejercicio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_EJER_NOMBRE, ejercicio.getNombreEjercicio());
        values.put(COL_EJER_SERIES, ejercicio.getSeries());
        values.put(COL_EJER_RUTINA_ID, ejercicio.getRutinaId());

        long id = db.insert(TABLE_EJERCICIOS, null, values);
        return id;
    }

    public long actualizarEjercicio(Ejercicio ejercicio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_EJER_NOMBRE, ejercicio.getNombreEjercicio());
        values.put(COL_EJER_SERIES, ejercicio.getSeries());
        values.put(COL_EJER_RUTINA_ID, ejercicio.getRutinaId());

        return db.update(TABLE_EJERCICIOS, values, COL_EJER_ID + " = ?",
                new String[]{String.valueOf(ejercicio.getId())});
    }

    public void eliminarEjercicio(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EJERCICIOS, COL_EJER_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    public List<Ejercicio> obtenerEjercicios(){
        List<Ejercicio> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT e.id, e.nombre_ejercicio, e.series, e.rutina_id, r.nombre_rutina " +
                "FROM " + TABLE_EJERCICIOS + " e "+
                "INNER JOIN " + TABLE_RUTINAS + " r ON e.rutina_id = r.id " +
                "ORDER BY e.id DESC";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String nombreEjercicio = cursor.getString(1);
                int series = cursor.getInt(2);
                int rutinaId = cursor.getInt(3);
                String nombreRutina = cursor.getString(4);

                Ejercicio ejercicio = new Ejercicio(id, nombreEjercicio, series, rutinaId, nombreRutina);

                ejercicio.setNombreEjercicio(nombreRutina);
                lista.add(ejercicio);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }



}
