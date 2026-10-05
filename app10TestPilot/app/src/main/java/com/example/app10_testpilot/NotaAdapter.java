package com.example.app10_testpilot;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    private List<Nota> listaNotas;
    private OnNotaClickListener listener;

    // Interfaz para manejar el clic del botón Eliminar
    public interface OnNotaClickListener {
        void onDeleteClick(Nota nota);
    }

    public NotaAdapter(List<Nota> listaNotas, OnNotaClickListener listener) {
        this.listaNotas = listaNotas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Nota nota = listaNotas.get(position);

        // 1. Llenamos los datos usando los atributos que trajimos con el JOIN
        holder.tvAlumno.setText("Alumno: " + nota.getNombreAlumnoCache());
        holder.tvCarrera.setText("Carrera: " + nota.getCarreraAlumnoCache());
        holder.tvMateria.setText("Materia: " + nota.getMateria());
        holder.tvNota.setText("Nota: " + nota.getValorNota());

        // =========================================================
        // 2. REGLA DE NEGOCIO DEL EXAMEN (If/Else Visual)
        // =========================================================
        if (nota.getValorNota() >= 51) {
            holder.tvEstado.setText("✓ APROBADO");
            holder.tvEstado.setTextColor(Color.parseColor("#4CAF50")); // Verde opcional para asegurar nota
        } else {
            holder.tvEstado.setText("✗ REPROBADO");
            holder.tvEstado.setTextColor(Color.parseColor("#F44336")); // Rojo opcional
        }

        // 3. Activamos el botón de eliminar del diseño
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(nota));
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public static class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlumno, tvCarrera, tvMateria, tvNota, tvEstado;
        Button btnEliminar;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlumno = itemView.findViewById(R.id.tvItemAlumno);
            tvCarrera = itemView.findViewById(R.id.tvItemCarrera);
            tvMateria = itemView.findViewById(R.id.tvItemMateria);
            tvNota = itemView.findViewById(R.id.tvItemNota);
            tvEstado = itemView.findViewById(R.id.tvItemEstado);
            btnEliminar = itemView.findViewById(R.id.btnEliminarItem);
        }
    }
}