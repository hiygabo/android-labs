package com.example.app8crudsqlite;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.MiViewHolder> {
    private List<Ejercicio> listaEjercicios;
    private OnEjercicioClickListener listener;

    public interface OnEjercicioClickListener{
        void onEditClick (Ejercicio ejercicio);
        void onDeleteClick (Ejercicio ejercicio);
    }

    public EjercicioAdapter(List<Ejercicio> listaEjercicios, OnEjercicioClickListener listener) {
        this.listaEjercicios = listaEjercicios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position){
        Ejercicio ejercicio = listaEjercicios.get(position);
        holder.tvEjer.setText(ejercicio.getNombreEjercicio());
        holder.tvRutina.setText(ejercicio.getNombreRutina());
        holder.btnEditar.setOnClickListener(v -> listener.onEditClick(ejercicio));
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(ejercicio));
    }

    @Override
    public int getItemCount(){
        return listaEjercicios.size();
    }

    public static class MiViewHolder extends RecyclerView.ViewHolder{
        TextView tvEjer, tvRutina;
        Button btnEditar, btnEliminar;

        public MiViewHolder(@NonNull View itemView){
            super(itemView);
            tvEjer = itemView.findViewById(R.id.tvNomEjercicioItem);
            tvRutina = itemView.findViewById(R.id.tvNomRutinaItem);
            btnEditar = itemView.findViewById(R.id.btnEditarItem);
            btnEliminar = itemView.findViewById(R.id.btnEliminarItem);
        }
    }


}
