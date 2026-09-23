package com.gabo.app6_sqlite;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddPersonaActivity extends AppCompatActivity {

    private EditText etNombres, etApellidos, etCi;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        // Inicializamos el objeto de base de datos
        dbHelper = new DatabaseHelper(this);
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etCi = findViewById(R.id.etCi);
        btnSave = findViewById(R.id.btnGuardar);

        btnSave.setOnClickListener(v -> savePersona());
    }

    private void savePersona() {
        // Extrae el texto de los EditText
        String nombres = etNombres.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String ciStr = etCi.getText().toString().trim();

        // Simple validación: no permitir guardar vacío
        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(ciStr)) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Persona newPersona = new Persona(nombres, apellidos, ciStr);
        dbHelper.addPersona(newPersona);
        Toast.makeText(this, "Persona guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}