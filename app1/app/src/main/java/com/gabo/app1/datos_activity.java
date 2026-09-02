package com.gabo.app1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class datos_activity extends AppCompatActivity {
    private TextView idNomCompleto, idCarnet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idNomCompleto = findViewById(R.id.idNomCompleto);
        idCarnet = findViewById(R.id.idCarnet);

        String nombre = getIntent().getStringExtra("nombre");
        String apellidos = getIntent().getStringExtra("apellidos");
        String carnet = getIntent().getStringExtra("carnet");

        if (nombre == null) nombre = "";
        if (apellidos == null) apellidos = "";
        if (carnet == null) carnet = "";

        String carnetInvertido = new StringBuilder(carnet).reverse().toString();
        String nombreModificado = nombre + " " + apellidos;

        idNomCompleto.setText(nombreModificado);
        idCarnet.setText(carnetInvertido);

    }
}