package com.example.loginproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginproject.database.ContaDAO;

import com.example.loginproject.database.ClienteDAO;
import com.example.loginproject.database.LocalidadeDAO;
import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Contas;
import com.example.loginproject.database.model.Localidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ContasFragment extends Fragment {

    private AdapterConta adapterConta;
    private ContaDAO contaDAO;
    private AdapterClickListener adapterClickListener;
    private int clienteId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contas, container, false);

        clienteId = getArguments().getInt("cliente_id", -1);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        contaDAO = new ContaDAO(requireContext());

        RecyclerView recyclerView = view.findViewById(R.id.rv_contas);
        adapterConta = new AdapterConta(contaDAO.getContasCliente(clienteId), adapterClickListener);
        recyclerView.setAdapter(adapterConta);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

    return view;
    }
    public void setAdapterClickListener(AdapterClickListener listener){
        this.adapterClickListener = listener;
    }

    public int getClienteId() {
        return clienteId;
    }



    private List<Contas> buscarContasdoCliente(int clienteId) {
        List<Contas> listaDeContas = new ArrayList<>();

        // Use o ClienteDAO para buscar os clientes da localidade
        contaDAO = new ContaDAO(requireContext());
        listaDeContas = contaDAO.getContasCliente(clienteId);

        return listaDeContas;
    }

}