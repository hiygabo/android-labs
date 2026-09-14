package com.gabo.app4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText registro;
    private TextView mostrar;
    private final String grdArchivo = "archivoExterno.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro = findViewById(R.id.registro);
        mostrar = findViewById(R.id.mostrar);

        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnMostrar = findViewById(R.id.btnMostrar);

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                guardarExt(registro.getText().toString().trim());
            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mostrarExterno();
            }
        });

    }

    private void guardarExt( String reg) {
        if(reg.isEmpty()){
            Toast.makeText(this, "Ingresar un texto", Toast.LENGTH_SHORT).show();
        }

        File rutaExterna = getExternalFilesDir(null);
        if(rutaExterna == null) {
            Toast.makeText(this, "No se pudo acceder al almacenamiento", Toast.LENGTH_SHORT).show();
        }

        File archivo = new File(rutaExterna, grdArchivo);
        try(FileOutputStream gr = new FileOutputStream(archivo)){
            gr.write(reg.getBytes());
            gr.flush();

            registro.setText("");
            Toast.makeText(this, "Guardado en: "+ archivo.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch(IOException e){
            Toast.makeText(this, "Error al guardar el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarExterno(){
        File rutaExterna = getExternalFilesDir(null);
        if (rutaExterna == null){
            Toast.makeText(this, "No se pudo acceder al almacenamiento", Toast.LENGTH_SHORT).show();
        }

        File archivo = new File(rutaExterna, grdArchivo);
        if(!archivo.exists()){
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
            mostrar.setText("");
            return;
        }

        StringBuilder sb = new StringBuilder();
        try( FileInputStream res = new FileInputStream(archivo);
             InputStreamReader re = new InputStreamReader(res);
            BufferedReader reader = new BufferedReader(re)) {
            String linea;
            while((linea = reader.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            mostrar.setText(sb.toString());
        } catch(IOException e){
            Toast.makeText(this, "error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}