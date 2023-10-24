package com.example.loginproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ClientesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        // Recupera o identificador da localidade dos argumentos
        int localidadeId = getArguments().getInt("localidade_id", -1);

        // Agora, você pode usar localidadeId para buscar os clientes da localidade
        List<Cliente> clientes = buscarClientesDaLocalidade(localidadeId);

        // Configurar um RecyclerView para exibir os clientes
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewClientes);
        // Configurar um adapter e definir os clientes no RecyclerView
        ClientesAdapter adapter = new ClientesAdapter(clientes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
}