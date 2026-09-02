package com.gabo.app1;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText lblNombre, lblApellido, lblCarnet;
    private Button btnMostrar, btnSalir;
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
        lblNombre = findViewById(R.id.lblNombre);
        lblApellido = findViewById(R.id.lblApellido);
        lblCarnet = findViewById(R.id.lblCarnet);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnSalir = findViewById(R.id.btnSalir);

        btnMostrar.setOnClickListener(v -> {

            String nombre = lblNombre.getText().toString();
            String apellidos = lblApellido.getText().toString();
            String carnet = lblCarnet.getText().toString();

            Intent siguiente = new Intent(MainActivity.this, datos_activity.class);

            siguiente.putExtra("nombre", nombre);
            siguiente.putExtra("apellidos", apellidos);
            siguiente.putExtra("carnet", carnet);
            startActivity(siguiente);
        });

        btnSalir.setOnClickListener(v -> finish());

    }
}