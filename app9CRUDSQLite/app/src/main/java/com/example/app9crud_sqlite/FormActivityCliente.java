package com.example.app9crud_sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivityCliente extends AppCompatActivity {
    private EditText etNombreCliente;
    private EditText etApellidosCliente;
    private Button btnGuardarCliente;
    private Databasehelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);

        etNombreCliente = findViewById(R.id.etNombreCliente);
        etApellidosCliente = findViewById(R.id.etApellidosCliente);
        btnGuardarCliente = findViewById(R.id.btnGuardarCliente);

        dbhelper  = new Databasehelper(this);

        btnGuardarCliente.setOnClickListener(v -> guardarCliente());

    }

    public void guardarCliente(){
        String nombreCliente = etNombreCliente.getText().toString();
        String apellidoCliente = etApellidosCliente.getText().toString();

        Cliente cliente = new Cliente(nombreCliente, apellidoCliente);
        dbhelper.insertarCliente(cliente);
    }
}
