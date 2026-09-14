package com.gabo.form;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private final String nomArchivo = "DatosForm.txt";

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
        resultado = findViewById(R.id.resultado);
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
            public void onClick(View v) {
                leerArchivo();
            }
        });

    }

    private void guardarArchivo(String contenido) {
        try(FileOutputStream guarda = openFileOutput(nomArchivo, Context.MODE_PRIVATE)){
            guarda.write(contenido.getBytes());
            nombreCompleto.setText("");
            lugarNacimiento.setText("");
            ci.setText("");
            correo.setText("");
            direccion.setText("");
            ciudad.setText("");
            movil.setText("");
            Toast.makeText(this, "Guardado en: "+ getFilesDir(), Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }

    private void leerArchivo(){
        try(FileInputStream mostrar = openFileInput(nomArchivo);
            InputStreamReader isr = new InputStreamReader(mostrar);
            BufferedReader reader = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while((linea=reader.readLine()) != null){
                sb.append(linea).append("\n");
            }
            resultado.setText(sb.toString());
        }catch(Exception e) {
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_LONG).show();
        }
    }
}