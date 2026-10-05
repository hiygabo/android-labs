package com.example.app10_testpilot;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    // Variables de Alumno
    private EditText etNombre, etCarrera;
    private Button btnGuardarAlumno;

    // Variables de Nota
    private EditText etAlumnoId, etMateria, etNota;
    private Button btnGuardarNota;

    // Botón para volver
    private Button btnVolverLista;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // Enlazamos el XML Frankenstein

        dbHelper = new DatabaseHelper(this);

        // Referencias de Alumno
        etNombre = findViewById(R.id.etNombreAlumno);
        etCarrera = findViewById(R.id.etCarrera);
        btnGuardarAlumno = findViewById(R.id.btnGuardarAlumno);

        // Referencias de Nota
        etAlumnoId = findViewById(R.id.etAlumnoId);
        etMateria = findViewById(R.id.etMateria);
        etNota = findViewById(R.id.etNota);
        btnGuardarNota = findViewById(R.id.btnGuardarNota);

        btnVolverLista = findViewById(R.id.btnVolverLista);

        // ==========================================
        // LÓGICA: GUARDAR ALUMNO Y MOSTRAR ID
        // ==========================================
        btnGuardarAlumno.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String carrera = etCarrera.getText().toString().trim();

            if (nombre.isEmpty() || carrera.isEmpty()) return; // Ignora si está vacío por rapidez

            Alumno nuevoAlumno = new Alumno(nombre, carrera);

            // ¡EL TRUCO DEL EXAMEN! Guardamos y capturamos el ID generado
            long idGenerado = dbHelper.insertarAlumno(nuevoAlumno);

            if (idGenerado != -1) {
                // Mostramos el ID en pantalla para que el usuario lo copie abajo
                Toast.makeText(this, "Alumno guardado. SU ID ES: " + idGenerado, Toast.LENGTH_LONG).show();

                // Opcional para asegurar la nota: Auto-llenar el campo de abajo para no tener que tipearlo
                etAlumnoId.setText(String.valueOf(idGenerado));
            }
        });

        // ==========================================
        // LÓGICA: GUARDAR NOTA
        // ==========================================
        btnGuardarNota.setOnClickListener(v -> {
            String idStr = etAlumnoId.getText().toString().trim();
            String materia = etMateria.getText().toString().trim();
            String notaStr = etNota.getText().toString().trim();

            if (idStr.isEmpty() || materia.isEmpty() || notaStr.isEmpty()) return;

            int idAlumno = Integer.parseInt(idStr);
            double valorNota = Double.parseDouble(notaStr);

            Nota nuevaNota = new Nota(materia, valorNota, idAlumno);
            dbHelper.insertarNota(nuevaNota);

            Toast.makeText(this, "Nota guardada exitosamente", Toast.LENGTH_SHORT).show();

            // Limpiamos los campos de nota para poder registrar otra
            etMateria.setText("");
            etNota.setText("");
        });

        // ==========================================
        // LÓGICA: VOLVER
        // ==========================================
        btnVolverLista.setOnClickListener(v -> finish());
    }
}