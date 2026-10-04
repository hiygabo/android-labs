package com.example.app8crudsqlite;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormRutinaActivity extends AppCompatActivity {
    private EditText etNombreRutina;
    private Button btnGuardar;
    private Databasehelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_rutina);
         etNombreRutina = findViewById(R.id.etNombreRutina);
         btnGuardar = findViewById(R.id.btnGuardar);
         dbHelper = new Databasehelper(this);

         btnGuardar.setOnClickListener( v -> guardarRutina());
    }

    private void guardarRutina() {
        String nombre = etNombreRutina.getText().toString().trim();

        if(TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Ingrese un nombre para la rutina", Toast.LENGTH_LONG);
            return;
        }

        Rutina nuevaRutina = new Rutina(nombre);

        long resultado = dbHelper.insertarRutina(nuevaRutina);
        if(resultado != -1 ){
            Toast.makeText(this, "Rutina guardada con exito", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Error al guardar la rutina", Toast.LENGTH_SHORT).show();
        }

    }


}
