package com.example.loginproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Contas;

import java.util.List;

public class AdapterConta extends RecyclerView.Adapter<AdapterConta.MyViewHolder>{
    private List<Contas> contasList;
    private AdapterClickListener onClick;


    public AdapterConta(List<Contas> contasList, AdapterClickListener onClick){
        this.contasList = contasList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public AdapterConta.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contas, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterConta.MyViewHolder holder, int position) {
        Contas contas = contasList.get(position);
        holder.valor_compra.setText(String.valueOf((int) contas.getValor_compra()));

        // Configurar um clique no item do RecyclerView
        holder.itemView.setOnClickListener(view -> {
            if (onClick != null) {
                onClick.onItemClicked(contas);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contasList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView valor_compra;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            valor_compra = itemView.findViewById(R.id.valor_compra);
        }
    }
}
