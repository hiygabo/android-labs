package com.example.app9crud_sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class FormPedidoActivity extends AppCompatActivity {
    private EditText etNombrePedido;
    private EditText etFecha;
    private EditText etIdCliente;
    private Button btnGuardarPedido;
    private Databasehelper dbhelper;
    private Pedido pedidoAEditar = null;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pedido);

        etNombrePedido = findViewById(R.id.etNombrePedido);
        etFecha = findViewById(R.id.etFecha);
        etIdCliente = findViewById(R.id.etIdCliente);
        btnGuardarPedido = findViewById(R.id.btnGuardarPedido);

        dbhelper = new Databasehelper(this);

        if(getIntent().hasExtra("pedido_editar")){
            pedidoAEditar = (Pedido) getIntent().getSerializableExtra("pedido_editar");
            etNombrePedido.setText(pedidoAEditar.getNombrePedido());
            String fechaStr = pedidoAEditar.getFecha().toString();
            etFecha.setText(fechaStr);
            etIdCliente.setText(String.valueOf(pedidoAEditar.getIdCliente()));
            btnGuardarPedido.setText("Editar Pedido");

        }

        btnGuardarPedido.setOnClickListener( v -> guardarOEditar());
    }

    public void guardarOEditar(){
        String nombrePedido = etNombrePedido.getText().toString();
        String fechaStr = etFecha.getText().toString();
        LocalDateTime fecha = LocalDateTime.parse(fechaStr);
        String idClienteStr = etIdCliente.getText().toString();
        int idCliente = Integer.parseInt(idClienteStr);

        if(pedidoAEditar != null){
            pedidoAEditar.setNombrePedido(nombrePedido);
            pedidoAEditar.setFecha(fecha);
            pedidoAEditar.setIdCliente(idCliente);
            dbhelper.actualizarPedido(pedidoAEditar);
        }else{
            Pedido pedido = new Pedido(nombrePedido, fecha, idCliente);
            dbhelper.insertarPedido(pedido);
        }
        finish();

    }


}
