package com.example.app9crud_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MainActivity extends AppCompatActivity implements PedidoAdapter.OnPedidoClickListener{
    private Button btnCrearCliente;
    private Button btnCrearPedido;
    private RecyclerView rvPedidos;
    private Databasehelper dbhelper;
    private PedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCrearCliente = findViewById(R.id.btnCrearCliente);
        btnCrearPedido = findViewById(R.id.button2);
        rvPedidos = findViewById(R.id.rvPedidos);

        dbhelper = new Databasehelper(this);

        rvPedidos.setLayoutManager(new LinearLayoutManager(this));

        btnCrearCliente.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, FormActivityCliente.class);
            startActivity(intent);
        });

        btnCrearPedido.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, FormPedidoActivity.class);
            startActivity(intent);
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        cargarLista();
    }

    public void cargarLista(){
        List<Pedido> lista = dbhelper.obtenerPedidos();
        adapter = new PedidoAdapter(lista, this);
        rvPedidos.setAdapter(adapter);
    }

    @Override
    public void onEditClick(Pedido pedido){
        Intent intent = new Intent(MainActivity.this, FormPedidoActivity.class);
        intent.putExtra("pedido_editar", pedido);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Pedido pedido){
        dbhelper.eliminarPedido(pedido.getIdPedido());
        cargarLista();
    }


}