package com.example.app9crud_sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MiViewHolder> {
    private List<Pedido> listaPedidos;
    private OnPedidoClickListener listener;

    public interface OnPedidoClickListener{
        void onEditClick(Pedido pedido);
        void onDeleteClick(Pedido pedido);
    }

    public PedidoAdapter(List<Pedido> listaPedidos,OnPedidoClickListener listener){
        this.listaPedidos = listaPedidos;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position){
        Pedido pedido = listaPedidos.get(position);
        holder.tvNombrePedido.setText(pedido.getNombrePedido());
        holder.tvNombreCliente.setText(pedido.getNombreCliente());
        holder.btnEliminarItem.setOnClickListener(v -> listener.onDeleteClick(pedido));
        holder.btnEditarItem.setOnClickListener(v -> listener.onEditClick(pedido));
    }
    @Override
    public int getItemCount(){
        return listaPedidos.size();
    }


    public static class MiViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombrePedido;
        TextView tvNombreCliente;
        Button btnEditarItem;
        Button btnEliminarItem;

        public MiViewHolder(@NonNull View itemView){
            super(itemView);
            tvNombrePedido = itemView.findViewById(R.id.tvNombrePedido);
            tvNombreCliente = itemView.findViewById(R.id.tvNombreCliente);
            btnEditarItem = itemView.findViewById(R.id.btnEditarItem);
            btnEliminarItem = itemView.findViewById(R.id.btnEliminarItem);
        }
    }

}
