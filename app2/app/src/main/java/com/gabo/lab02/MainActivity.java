package com.gabo.lab02;

import android.content.Context;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private EditText Escribe;
    private TextView Resultado;
    private final String nomArchivo = "EjemPersistencia.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Escribe = findViewById(R.id.Escribe);
        Resultado = findViewById(R.id.Resultado);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnLeer = findViewById(R.id.btnLeer);

        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String textoaGuardar = Escribe.getText().toString();
                guardarArchivo(textoaGuardar);
            }
        });
        btnLeer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                leerArchivo();
            }
        });
    }

    private void guardarArchivo(String contenido) {
        try(FileOutputStream guarda = openFileOutput(nomArchivo, Context.MODE_PRIVATE)){
            guarda.write(contenido.getBytes());
            Escribe.setText("");
            Toast.makeText(this, "Guardado en: " + getFilesDir(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT);
        }

    }
    public void leerArchivo() {
        try(FileInputStream mostrar = openFileInput(nomArchivo);
            InputStreamReader isr = new InputStreamReader(mostrar);
            BufferedReader reader = new BufferedReader(isr))
        {
            StringBuilder sb = new StringBuilder();
            String linea;
            while((linea=reader.readLine()) != null){
                sb.append(linea).append("\n");
            }
            Resultado.setText(sb.toString());
        }catch(Exception e){
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_LONG).show();
        }
    }
}