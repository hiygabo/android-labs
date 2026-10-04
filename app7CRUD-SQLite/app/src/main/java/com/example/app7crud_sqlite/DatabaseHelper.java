package com.example.app7crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personas.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PERSONAS =  "personas";

    public static final String COLUMN_ID = "id_persona";
    public static final String COLUMN_NOMBRES = "nombres";
    public static final String COLUMN_APELLIDOS = "apellidos";
    public static final String COLUMN_CI = "ci";

    private static final String CREATE_TABLE = "CREATE TABLE" + TABLE_PERSONAS + "("
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT"
            + COLUMN_NOMBRES + "TEXT NOT NULL"
            + COLUMN_APELLIDOS + "TEXT NOT NULL"
            + COLUMN_CI + "TEXT NOT NULL"
            + ")";
    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_PERSONAS);
        onCreate(db);
    }

    public List<Persona> getAllPersonas(){
         List<Persona> lista = new ArrayList<>();
         String query = "SELECT * FROM " + TABLE_PERSONAS;

         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(query, null);

         if(cursor.moveToFirst()){
             do{
                 Persona persona = new Persona(
                         cursor.getInt(0),
                         cursor.getString(1),
                         cursor.getString(2),
                         cursor.getString(3)
                 );
             }while(cursor.moveToNext());
         }
         cursor.close();
         return lista;
    }

    public long addPersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRES, persona.getNombres());
        values.put(COLUMN_APELLIDOS, persona.getApellidos());
        values.put(COLUMN_CI, persona.getCi());
        long id = db.insert(TABLE_PERSONAS, null, values);
         return id;
    }

}
