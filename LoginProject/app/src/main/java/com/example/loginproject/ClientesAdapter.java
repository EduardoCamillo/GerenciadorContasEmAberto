package com.example.loginproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Localidade;

import java.util.List;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.MyViewHolder>{

    private List<Cliente> clienteList;
    private AdapterClickListener onClick;



    public ClientesAdapter(List<Cliente> clienteList, AdapterClickListener onClick){
        this.clienteList = clienteList;
        this.onClick = onClick;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clientes, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cliente cliente = clienteList.get(position);
        holder.nome_cliente.setText(cliente.getNomeCliente());

        // Configurar um clique no item do RecyclerView
        holder.itemView.setOnClickListener(view -> {
            if (onClick != null) {
                onClick.onItemClicked(cliente);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        //declarar os componentes que irão conter no layout (neste caso é apenas um TextView)

        TextView nome_cliente;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome_cliente = itemView.findViewById(R.id.nome_cliente);
        }
    }
}
