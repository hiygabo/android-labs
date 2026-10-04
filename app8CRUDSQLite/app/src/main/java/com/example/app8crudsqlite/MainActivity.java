package com.example.app8crudsqlite;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EjercicioAdapter.OnEjercicioClickListener {

    private Button btnNuevaRutina, btnNuevoEjercicio;
    private RecyclerView rvEjercicios;
    private EjercicioAdapter adapter;
    private Databasehelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNuevaRutina = findViewById(R.id.btnCrearRutina);
        btnNuevoEjercicio = findViewById(R.id.btnCrearEjercicio);

        rvEjercicios = findViewById(R.id.rvEjercicios);

        dbHelper = new Databasehelper(this);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(this));

        btnNuevaRutina.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormRutinaActivity.class);
            startActivity(intent);
        });

        btnNuevoEjercicio.setOnClickListener( v-> {
            Intent intent = new Intent(MainActivity.this, FormEjercicioActivity.class);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    private void cargarLista(){
        List<Ejercicio> lista = dbHelper.obtenerEjercicios();
        adapter = new EjercicioAdapter(lista,this);
        rvEjercicios.setAdapter(adapter);
    }

    @Override
    public void onEditClick(Ejercicio ejercicio){
        Intent intent = new Intent(this, FormEjercicioActivity.class);
        intent.putExtra("ejercicio_editar", ejercicio);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Ejercicio ejercicio){
        dbHelper.eliminarEjercicio(ejercicio.getId());
        cargarLista();
    }
}