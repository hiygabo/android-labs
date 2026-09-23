package com.gabo.app6_sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    private List<Persona> listaPersonas;

    public PersonaAdapter(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = listaPersonas.get(position);
        holder.tvNombreCompleto.setText(persona.getNombres() + " " + persona.getApellidos());
        holder.tvCiItem.setText("CI: " + persona.getCi());
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreCompleto, tvCiItem;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCompleto = itemView.findViewById(R.id.tvNombreCompleto);
            tvCiItem = itemView.findViewById(R.id.tvCiItem);
        }
    }
}