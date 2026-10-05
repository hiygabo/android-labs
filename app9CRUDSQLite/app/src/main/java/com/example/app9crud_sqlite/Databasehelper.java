package com.example.app9crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pedidos_db";
    private static int DATABASE_VERSION = 1;

    private static final String TABLE_CLIENTES = "clientes";
    private static final String COLUMN_ID_CLIENTE = "id_cliente";

    private static final String COLUMN_NOMBRE_CLIENTE = "nombre_cliente";
    private static final String COLUMN_APELLIDO_CLIENTE = "apellido_cliente";


    private static final String TABLE_PEDIDOS = "pedidos";
    private static final String COLUMN_ID_PEDIDO = "id_pedido";
    private static final String COLUMN_NOMBRE_PEDIDO = "nombre_pedido";
    private static final String COLUMN_FECHA_PEDIDO = "fecha_pedido";
    private static final String COLUMN_PEDIDO_ID_CLIENTE = "id_cliente";


    private static final String CREATE_TABLE_CLIENTES = "CREATE TABLE "+ TABLE_CLIENTES + "("
            + COLUMN_ID_CLIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOMBRE_CLIENTE + " TEXT NOT NULL, "
            + COLUMN_APELLIDO_CLIENTE + " TEXT NOT NULL "
            + ");";


    private static final String CREATE_TABLE_PEDIDOS = "CREATE TABLE " + TABLE_PEDIDOS + "("
            + COLUMN_ID_PEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOMBRE_PEDIDO + " TEXT NOT NULL, "
            + COLUMN_FECHA_PEDIDO + " TEXT NOT NULL, "
            + COLUMN_PEDIDO_ID_CLIENTE + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + COLUMN_PEDIDO_ID_CLIENTE + ") REFERENCES " + TABLE_CLIENTES + "(" + COLUMN_ID_CLIENTE + ")"
            + ");";

    public Databasehelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_CLIENTES);
        db.execSQL(CREATE_TABLE_PEDIDOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDOS);
        onCreate(db);
    }


    public long insertarCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOMBRE_CLIENTE, cliente.getNombre());
        values.put(COLUMN_APELLIDO_CLIENTE, cliente.getApellido());

        long id = db.insert(TABLE_CLIENTES, null, values);

        return id;
    }

    public long insertarPedido(Pedido pedido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOMBRE_PEDIDO, pedido.getNombrePedido());
        values.put(COLUMN_FECHA_PEDIDO, pedido.getFecha().toString());
        values.put(COLUMN_PEDIDO_ID_CLIENTE, pedido.getIdCliente());

        long id = db.insert(TABLE_PEDIDOS, null, values);
        return id;
    }

    public List<Pedido> obtenerPedidos(){
        List<Pedido> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT p.id_pedido, p.nombre_pedido, p.fecha_pedido, p.id_cliente,c.nombre_cliente, c.apellido_cliente "
                + " FROM " + TABLE_PEDIDOS + " p "
                + " INNER JOIN " + TABLE_CLIENTES + " c ON p.id_cliente = c.id_cliente"
                + " ORDER BY p.id_pedido DESC ";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                int idPedido = cursor.getInt(0);
                String nombrePedido = cursor.getString(1);
                LocalDateTime fechaPedido = LocalDateTime.parse(cursor.getString(2));
                int idCliente = cursor.getInt(3);
                String nombreCliente = cursor.getString(4);
                String apellidoCliente = cursor.getString(5);

                Pedido pedido = new Pedido(idPedido, nombrePedido, fechaPedido, idCliente, nombreCliente, apellidoCliente);
                lista.add(pedido);

            }while(cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public long actualizarPedido(Pedido pedido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOMBRE_PEDIDO, pedido.getNombrePedido());
        values.put(COLUMN_FECHA_PEDIDO, pedido.getFecha().toString());
        values.put(COLUMN_PEDIDO_ID_CLIENTE, pedido.getIdCliente());

        return db.update(TABLE_PEDIDOS, values, COLUMN_ID_PEDIDO + " = ?",
                new String[]{String.valueOf(pedido.getIdPedido())});
    }

    public long eliminarPedido(int idPedido){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PEDIDOS, COLUMN_ID_PEDIDO + " = ?",
                new String[]{String.valueOf(idPedido)});
    }


}
