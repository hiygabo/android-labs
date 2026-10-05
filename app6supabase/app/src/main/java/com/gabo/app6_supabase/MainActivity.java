package com.gabo.app6_supabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MascotaAdapter.OnMascotaClickListener {

    private RecyclerView recyclerMascotas;
    private MascotaAdapter adapter;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerMascotas = findViewById(R.id.recyclerMascotas);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerMascotas.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormMascotaActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarMascotas(); // Se ejecuta al abrir la app o al volver de guardar
    }

    private void cargarMascotas() {
        // Petición HTTP GET a Supabase
        RetrofitClient.getApi().getMascotas().enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new MascotaAdapter(response.body(), MainActivity.this);
                    recyclerMascotas.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Mascota>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditClick(Mascota mascota) {
        Intent intent = new Intent(this, FormMascotaActivity.class);
        intent.putExtra("mascota", mascota); // Enviamos la mascota entera para editar
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Mascota mascota) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Mascota")
                .setMessage("¿Seguro que deseas eliminar a " + mascota.getNombre() + "?")
                .setPositiveButton("Eliminar", (dialog, which) -> eliminarEnSupabase(mascota.getId()))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarEnSupabase(Long id) {
        // En Supabase, para apuntar a un ID específico, se envía "eq.ID"
        RetrofitClient.getApi().deleteMascota("eq." + id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Mascota eliminada", Toast.LENGTH_SHORT).show();
                    cargarMascotas(); // Recargamos la lista
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}