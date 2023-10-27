package com.example.loginproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.loginproject.database.ClienteDAO;
import com.example.loginproject.database.LocalidadeDAO;
import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Localidade;

import java.util.ArrayList;
import java.util.List;

public class ClientesFragments extends Fragment {

    private ClientesAdapter clientesAdapter;
    private ClienteDAO clienteDAO;
    private AdapterClickListener adapterClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes_fragments, container, false);

        // Recupera o identificador da localidade dos argumentos
        int localidadeId = getArguments().getInt("localidade_id", -1);

        clienteDAO = new ClienteDAO(requireContext());

        // Agora, você pode usar localidadeId para buscar os clientes da localidade
        List<Cliente> clientes = buscarClientesDaLocalidade(localidadeId);

        // Configurar um RecyclerView para exibir os clientes
        RecyclerView recyclerView = view.findViewById(R.id.rv_clientes);
        // Configurar um adapter e definir os clientes no RecyclerView
        clientesAdapter = new ClientesAdapter(clienteDAO.getClientesDaLocalidade(localidadeId), adapterClickListener);
        recyclerView.setAdapter(clientesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    public void setAdapterClickListener(AdapterClickListener listener){
        this.adapterClickListener = listener;
    }

    private List<Cliente> buscarClientesDaLocalidade(int localidadeId) {
        List<Cliente> listaDeClientes = new ArrayList<>();

        // Use o ClienteDAO para buscar os clientes da localidade
        clienteDAO = new ClienteDAO(requireContext());
        listaDeClientes = clienteDAO.getClientesDaLocalidade(localidadeId);

        return listaDeClientes;
    }


}