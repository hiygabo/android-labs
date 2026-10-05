package com.gabo.app6_supabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.gabo.app6_supabase.Mascota;
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private List<Mascota> mascotaList;
    private OnMascotaClickListener listener;

    // Interfaz para comunicar clics al MainActivity
    public interface OnMascotaClickListener {
        void onEditClick(Mascota mascota);
        void onDeleteClick(Mascota mascota);
    }

    public MascotaAdapter(List<Mascota> mascotaList, OnMascotaClickListener listener) {
        this.mascotaList = mascotaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        Mascota mascota = mascotaList.get(position);
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRaza.setText(mascota.getRaza());
        holder.tvEdad.setText(mascota.getEdad() + " años");
        holder.tvColor.setText(mascota.getColor());

        // Eventos de clic
        holder.btnEditar.setOnClickListener(v -> listener.onEditClick(mascota));
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(mascota));
    }

    @Override
    public int getItemCount() {
        return mascotaList.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvRaza, tvEdad, tvColor;
        Button btnEditar, btnEliminar;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRaza = itemView.findViewById(R.id.tvRaza);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            tvColor = itemView.findViewById(R.id.tvColor);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}