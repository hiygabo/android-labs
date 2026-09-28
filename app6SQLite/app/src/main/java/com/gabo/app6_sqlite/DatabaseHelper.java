package com.gabo.app6_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre y versión del archivo local de la base de datos
    private static final String DATABASE_NAME = "personas.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PERSONAS = "personas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRES = "nombres";
    public static final String COLUMN_APELLIDOS = "apellidos";
    public static final String COLUMN_CI = "ci";

    // Creacion de la tabla
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_PERSONAS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRES + " TEXT NOT NULL,"
            + COLUMN_APELLIDOS + " TEXT NOT NULL,"
            + COLUMN_CI + " TEXT NOT NULL"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAS);
        onCreate(db);
    }

    // CRUD - Create
    public long addPersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRES, persona.getNombres());
        values.put(COLUMN_APELLIDOS, persona.getApellidos());
        values.put(COLUMN_CI, persona.getCi());
        long id = db.insert(TABLE_PERSONAS, null, values);
        //db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Persona> getAllPersonas() {
        // Devuelve los resultados más recientes primero (DESC)
        List<Persona> personas = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PERSONAS
                + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Persona persona = new Persona(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRES)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDOS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CI))
                );
                personas.add(persona);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personas;
    }

    // CRUD: Update
    public int updatePersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRES, persona.getNombres());
        values.put(COLUMN_APELLIDOS, persona.getApellidos());
        values.put(COLUMN_CI, persona.getCi());

        return db.update(TABLE_PERSONAS, values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(persona.getId())});
    }

    // CRUD: Delete
    public void deletePersona(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSONAS,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}