package com.example.app8crudsqlite;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormEjercicioActivity extends AppCompatActivity {

    private EditText etNombreEjercicio, etSeries, etRutinaId;
    private Button btnGuardarEjercicio;
    private Databasehelper dbHelper;
    private Ejercicio ejercicioAEditar = null;

    @Override
    protected void onCreate(Bundle savedInstanceSatate) {
        super.onCreate(savedInstanceSatate);
        setContentView(R.layout.activity_form_ejercicio);

        etNombreEjercicio = findViewById(R.id.etNombreEjercicio);
        etSeries = findViewById(R.id.etSeries);
        etRutinaId = findViewById(R.id.etRutinaId);
        btnGuardarEjercicio = findViewById(R.id.btnGuardarEjercicio);

        dbHelper = new Databasehelper(this);

        if(getIntent().hasExtra("ejercicio_editar")){
            ejercicioAEditar = (Ejercicio) getIntent().getSerializableExtra("ejercicio_editar");

            etNombreEjercicio.setText(ejercicioAEditar.getNombreEjercicio());
            etSeries.setText(String.valueOf(ejercicioAEditar.getSeries()));
            etRutinaId.setText(String.valueOf(ejercicioAEditar.getRutinaId()));
            btnGuardarEjercicio.setText("Actualizar");
        }
        btnGuardarEjercicio.setOnClickListener(v -> guardarOEditarEjercicio());
    }

    private void guardarOEditarEjercicio(){
        String nombreEjercicio = etNombreEjercicio.getText().toString().trim();
        String seriesStr = etSeries.getText().toString().trim();
        String rutinaIdStr = etRutinaId.getText().toString().trim();

        if(TextUtils.isEmpty(nombreEjercicio) || TextUtils.isEmpty(seriesStr) || TextUtils.isEmpty(rutinaIdStr) ){
            Toast.makeText(this, "Los campos no pueden", Toast.LENGTH_SHORT).show();
            return;
        }
        int series = Integer.parseInt(seriesStr);
        int rutinaId = Integer.parseInt(rutinaIdStr);

        if(ejercicioAEditar != null){
            ejercicioAEditar.setNombreEjercicio(nombreEjercicio);
            ejercicioAEditar.setSeries(series);
            ejercicioAEditar.setRutinaId(rutinaId);
            dbHelper.actualizarEjercicio(ejercicioAEditar);
            Toast.makeText(this, "Ejercicio actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Ejercicio nuevoEjercicio = new Ejercicio(nombreEjercicio, series, rutinaId);
            dbHelper.insertarEjercicio(nuevoEjercicio);
            Toast.makeText(this, "Ejercicio guardado correctamente", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
}
