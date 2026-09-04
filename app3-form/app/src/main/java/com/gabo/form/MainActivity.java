package com.gabo.form;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private EditText nombreCompleto;
    private EditText lugarNacimiento;
    private EditText ci;
    private EditText correo;
    private EditText direccion;
    private EditText ciudad;
    private EditText movil;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreCompleto = findViewById(R.id.etNombre);
        lugarNacimiento = findViewById(R.id.etLugar);
        ci = findViewById(R.id.etCi);
        correo = findViewById(R.id.etCorreo);
        direccion = findViewById(R.id.etDireccion);
        ciudad = findViewById(R.id.etCiudad);
        movil = findViewById(R.id.etMovil);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnLeer = findViewById(R.id.btnLeer);

        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nombreGuardar = nombreCompleto.getText().toString();
                String lugarNacimientoGuardar= lugarNacimiento.getText().toString();
                String ciGuardar= ci.getText().toString();
                String correoGuardar= correo.getText().toString();
                String direccionGuardar= direccion.getText().toString();
                String ciudadGuardar= ciudad.getText().toString();
                String movilGuardar= movil.getText().toString();

                String datosEmpaquetados =
                        "Nombre: " + nombreGuardar + "\n" +
                                "Lugar de Nac.: " + lugarNacimientoGuardar + "\n" +
                                "CI: " + ciGuardar + "\n" +
                                "Correo: " + correoGuardar + "\n" +
                                "Dirección: " + direccionGuardar + "\n" +
                                "Ciudad: " + ciudadGuardar + "\n" +
                                "Móvil: " + movilGuardar;
                
                guardarArchivo(datosEmpaquetados);


            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                leerArchivo();
            }
        });

    }
}