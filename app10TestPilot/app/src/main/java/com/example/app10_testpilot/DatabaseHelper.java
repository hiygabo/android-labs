package com.example.app10_testpilot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "universidad.db";
    private static final int DATABASE_VERSION = 1;

    // TABLA ALUMNO
    private static final String TABLE_ALUMNO = "alumno";
    private static final String COL_ALU_ID = "id_alumno";
    private static final String COL_ALU_NOMBRE = "nombre";
    private static final String COL_ALU_CARRERA = "carrera";

    // TABLA NOTA
    private static final String TABLE_NOTA = "nota";
    private static final String COL_NOT_ID = "id_nota";
    private static final String COL_NOT_MATERIA = "materia";
    private static final String COL_NOT_VALOR = "valor_nota";
    private static final String COL_NOT_ALUMNO_ID = "id_alumno"; // Columna manual sin Foreign Key

    // CREATE TABLES (Sin restricciones de llaves foráneas)
    private static final String CREATE_TABLE_ALUMNO = "CREATE TABLE " + TABLE_ALUMNO + "("
            + COL_ALU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_ALU_NOMBRE + " TEXT NOT NULL, "
            + COL_ALU_CARRERA + " TEXT NOT NULL"
            + ");";

    private static final String CREATE_TABLE_NOTA = "CREATE TABLE " + TABLE_NOTA + "("
            + COL_NOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOT_MATERIA + " TEXT NOT NULL, "
            + COL_NOT_VALOR + " REAL NOT NULL, "
            + COL_NOT_ALUMNO_ID + " INTEGER NOT NULL"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALUMNO);
        db.execSQL(CREATE_TABLE_NOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUMNO);
        onCreate(db);
    }

    // ==========================================
    // MÉTODOS CRUD
    // ==========================================

    // Retorna el ID generado para que lo muestres en el Toast como pide el examen
    public long insertarAlumno(Alumno alumno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ALU_NOMBRE, alumno.getNombre());
        values.put(COL_ALU_CARRERA, alumno.getCarrera());
        return db.insert(TABLE_ALUMNO, null, values);
    }

    public long insertarNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOT_MATERIA, nota.getMateria());
        values.put(COL_NOT_VALOR, nota.getValorNota());
        values.put(COL_NOT_ALUMNO_ID, nota.getAlumnoId());
        return db.insert(TABLE_NOTA, null, values);
    }

    // El JOIN funciona perfectamente aunque no haya FOREIGN KEY
    public List<Nota> obtenerNotasConAlumnos() {
        List<Nota> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT n.id_nota, n.materia, n.valor_nota, n.id_alumno, a.nombre, a.carrera " +
                "FROM " + TABLE_NOTA + " n " +
                "INNER JOIN " + TABLE_ALUMNO + " a ON n.id_alumno = a.id_alumno " +
                "ORDER BY n.id_nota DESC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idNota = cursor.getInt(0);
                String materia = cursor.getString(1);
                double valor = cursor.getDouble(2);
                int idAlumno = cursor.getInt(3);
                String nombreAlu = cursor.getString(4);
                String carreraAlu = cursor.getString(5);

                Nota nota = new Nota(idNota, materia, valor, idAlumno);
                nota.setNombreAlumnoCache(nombreAlu);
                nota.setCarreraAlumnoCache(carreraAlu);

                lista.add(nota);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    // Método extra: Borrar nota (Pide un botón eliminar en la lista)
    public void eliminarNota(int idNota) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTA, COL_NOT_ID + " = ?", new String[]{String.valueOf(idNota)});
    }
}