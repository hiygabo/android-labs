package com.gabo.app5_datapersistenceform;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etCi, etDepto, etCelular;
    private CheckBox cbMasculino, cbFemenino;
    private TextView tvMostrar;

    private final String grdArchivo = "archivoExterno.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etCi = findViewById(R.id.etCi);
        etDepto = findViewById(R.id.etDepto);
        etCelular = findViewById(R.id.etCelular);
        cbMasculino = findViewById(R.id.cbMasculino);
        cbFemenino = findViewById(R.id.cbFemenino);
        tvMostrar = findViewById(R.id.tvMostrar);

        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnLeer = findViewById(R.id.btnLeer);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String ci = etCi.getText().toString().trim();
                String depto = etDepto.getText().toString().trim();
                String celular = etCelular.getText().toString().trim();


                String genero = "No especificado";
                if (cbMasculino.isChecked() && !cbFemenino.isChecked()) {
                    genero = "Masculino";
                } else if (cbFemenino.isChecked() && !cbMasculino.isChecked()) {
                    genero = "Femenino";
                } else if (cbMasculino.isChecked() && cbFemenino.isChecked()) {
                    genero = "Masculino y Femenino";
                }


                if (nombre.isEmpty() || ci.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Llena al menos nombre y CI", Toast.LENGTH_SHORT).show();
                    return;
                }

                String datosEmpaquetados =
                        "Nombre: " + nombre + "\n" +
                                "C.I.: " + ci + "\n" +
                                "Depto: " + depto + "\n" +
                                "Género: " + genero + "\n" +
                                "Celular: " + celular ;


                guardarExt(datosEmpaquetados);
            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarExterno();
            }
        });
    }


    private void guardarExt(String reg) {
        File rutaExterna = getExternalFilesDir(null);
        if (rutaExterna == null) {
            Toast.makeText(this, "No se pudo acceder al almacenamiento", Toast.LENGTH_SHORT).show();
            return;
        }

        File archivo = new File(rutaExterna, grdArchivo);

        try (FileOutputStream gr = new FileOutputStream(archivo)) {
            gr.write(reg.getBytes());
            gr.flush();

            etNombre.setText("");
            etCi.setText("");
            etDepto.setText("");
            etCelular.setText("");
            cbMasculino.setChecked(false);
            cbFemenino.setChecked(false);

            Toast.makeText(this, "Guardado en: " + archivo.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarExterno() {
        File rutaExterna = getExternalFilesDir(null);
        if (rutaExterna == null) {
            Toast.makeText(this, "No se pudo acceder al almacenamiento", Toast.LENGTH_SHORT).show();
            return;
        }

        File archivo = new File(rutaExterna, grdArchivo);
        if (!archivo.exists()) {
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
            tvMostrar.setText("");
            return;
        }

        StringBuilder sb = new StringBuilder();
        try (FileInputStream res = new FileInputStream(archivo);
             InputStreamReader re = new InputStreamReader(res);
             BufferedReader reader = new BufferedReader(re)) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            tvMostrar.setText(sb.toString());

        } catch (IOException e) {
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}

