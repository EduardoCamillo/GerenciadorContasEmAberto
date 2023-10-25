package com.example.loginproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.database.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientesFragments extends Fragment {

    private ClientesAdapter clientesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes_fragments, container, false);

        // Recupera o identificador da localidade dos argumentos
        int localidadeId = getArguments().getInt("localidade_id", -1);

        // Agora, você pode usar localidadeId para buscar os clientes da localidade
        List<Cliente> clientes = buscarClientesDaLocalidade(localidadeId);

        // Configurar um RecyclerView para exibir os clientes
        RecyclerView recyclerView = view.findViewById(R.id.rv_clientes);
        // Configurar um adapter e definir os clientes no RecyclerView
        ClientesAdapter adapter = new ClientesAdapter(clientes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
    private List<Cliente> buscarClientesDaLocalidade(int localidadeId) {
        // Substitua este exemplo por sua lógica real de busca de clientes no banco de dados ou em outra fonte de dados.

        List<Cliente> listaDeClientes = new ArrayList<>();

        // Suponha que você tenha uma classe Cliente com idLocalidade para identificar a localidade de cada cliente.
        // Neste exemplo, estamos apenas criando alguns clientes fictícios.

        // Simule a busca de clientes no banco de dados ou na fonte de dados real
        for (int i = 1; i <= 10; i++) {
            // Suponha que 1 a 5 clientes pertençam à localidade com o localidadeId fornecido.
            if (i % 2 == 0) {
                Cliente cliente = new Cliente(localidadeId, i, "nome cliente" );
                listaDeClientes.add(cliente);
            }
        }

        return listaDeClientes;
    }
}