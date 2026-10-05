package com.example.app10_testpilot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Implementamos la interfaz para poder borrar
public class MainActivity extends AppCompatActivity implements NotaAdapter.OnNotaClickListener {

    private Button btnIrARegistro;
    private RecyclerView rvNotas;
    private NotaAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // El diseño con la lista y el botón de registro superior

        btnIrARegistro = findViewById(R.id.btnIrARegistro);
        rvNotas = findViewById(R.id.rvNotas);

        dbHelper = new DatabaseHelper(this);
        rvNotas.setLayoutManager(new LinearLayoutManager(this));

        // Navegación a la pantalla de Registro "Frankenstein"
        btnIrARegistro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        });
    }

    // Usamos onResume para que la lista se actualice sola al volver de registrar una nota
    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    private void cargarLista() {
        // Ejecutamos el JOIN que une las notas con sus respectivos alumnos
        List<Nota> lista = dbHelper.obtenerNotasConAlumnos();
        adapter = new NotaAdapter(lista, this);
        rvNotas.setAdapter(adapter);
    }

    // Método de la interfaz para eliminar un registro cuando se presiona el botón gris de la fila
    @Override
    public void onDeleteClick(Nota nota) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Eliminar la nota de " + nota.getMateria() + " de este alumno?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    dbHelper.eliminarNota(nota.getIdNota());
                    cargarLista(); // Refrescar la pantalla
                    Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}