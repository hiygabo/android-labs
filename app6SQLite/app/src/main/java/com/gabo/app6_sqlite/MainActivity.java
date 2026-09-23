package com.gabo.app6_sqlite;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private RecyclerView rvPersonas;
    private DatabaseHelper dbHelper;
    private PersonaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        rvPersonas = findViewById(R.id.rvPersonas); // Enlazamos el RecyclerView del XML de tu docente
        dbHelper = new DatabaseHelper(this);

        // Configuramos cómo se mostrará la lista (hacia abajo)
        rvPersonas.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPersonaActivity.class);
            startActivity(intent);
        });
    }

    // onResume se ejecuta al abrir la app Y al volver del formulario
    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    private void cargarLista() {
        // 1. Pedimos los datos a la BD
        List<Persona> lista = dbHelper.getAllPersonas();

        // 2. Se los pasamos al Adapter
        adapter = new PersonaAdapter(lista);

        // 3. Conectamos el Adapter al RecyclerView
        rvPersonas.setAdapter(adapter);
    }
}