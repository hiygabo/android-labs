package com.gabo.app6_supabase;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormMascotaActivity extends AppCompatActivity {

    private TextView tvTituloForm;
    private EditText etNombre, etRaza, etEdad, etColor;
    private Button btnGuardar;
    private Mascota mascotaEditando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_mascota);

        tvTituloForm = findViewById(R.id.tvTituloForm);
        etNombre = findViewById(R.id.etNombre);
        etRaza = findViewById(R.id.etRaza);
        etEdad = findViewById(R.id.etEdad);
        etColor = findViewById(R.id.etColor);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Si existe el extra "mascota", estamos en modo EDICIÓN
        if (getIntent().hasExtra("mascota")) {
            mascotaEditando = (Mascota) getIntent().getSerializableExtra("mascota");
            tvTituloForm.setText("Editar Mascota");
            btnGuardar.setText("Actualizar");

            etNombre.setText(mascotaEditando.getNombre());
            etRaza.setText(mascotaEditando.getRaza());
            etEdad.setText(String.valueOf(mascotaEditando.getEdad()));
            etColor.setText(mascotaEditando.getColor());
        }

        btnGuardar.setOnClickListener(v -> procesarGuardado());
    }

    private void procesarGuardado() {
        String nombre = etNombre.getText().toString().trim();
        String raza = etRaza.getText().toString().trim();
        String edadStr = etEdad.getText().toString().trim();
        String color = etColor.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(raza) || TextUtils.isEmpty(edadStr) || TextUtils.isEmpty(color)) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int edad = Integer.parseInt(edadStr);

        if (mascotaEditando != null) {
            // ==========================================
            // MODO EDICIÓN (UPDATE / PATCH)
            // ==========================================
            mascotaEditando.setNombre(nombre);
            mascotaEditando.setRaza(raza);
            mascotaEditando.setEdad(edad);
            mascotaEditando.setColor(color);

            // Pasamos "eq.ID" para decirle a Supabase qué fila actualizar
            RetrofitClient.getApi().updateMascota("eq." + mascotaEditando.getId(), mascotaEditando)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(FormMascotaActivity.this, "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
                                finish(); // Cierra y regresa al Main
                            } else {
                                Toast.makeText(FormMascotaActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(FormMascotaActivity.this, "Falla de red", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            // ==========================================
            // MODO CREACIÓN (CREATE / POST)
            // ==========================================
            Mascota nuevaMascota = new Mascota(nombre, raza, edad, color);

            RetrofitClient.getApi().createMascota(nuevaMascota).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(FormMascotaActivity.this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
                        finish(); // Cierra y regresa al Main
                    } else {
                        Toast.makeText(FormMascotaActivity.this, "Error al guardar en BD", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(FormMascotaActivity.this, "Falla de red", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}