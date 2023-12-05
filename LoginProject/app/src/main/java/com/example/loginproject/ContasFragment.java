package com.example.loginproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginproject.database.ContaDAO;

import com.example.loginproject.database.ClienteDAO;
import com.example.loginproject.database.DBHelper;
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
    private TextView valorConta;

    private Button btn_apagar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contas, container, false);

        btn_apagar = view.findViewById(R.id.btn_apagar);
        valorConta = view.findViewById(R.id.result_final);
        clienteId = getArguments().getInt("cliente_id", -1);

        contaDAO = new ContaDAO(requireContext());
        float valorTotalContas = contaDAO.CalcularValor(clienteId);

        valorConta.setText("Total: " + String.valueOf(valorTotalContas)); // Define o valor no TextView

        clienteId = getArguments().getInt("cliente_id", -1);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        contaDAO = new ContaDAO(requireContext());

        RecyclerView recyclerView = view.findViewById(R.id.rv_contas);
        adapterConta = new AdapterConta(contaDAO.getContasCliente(clienteId), adapterClickListener);
        recyclerView.setAdapter(adapterConta);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        btn_apagar.setOnClickListener(v -> {
            ContaDAO contaDAO = new ContaDAO(requireContext());
            int clienteId = getArguments().getInt("cliente_id", -1);
            if (clienteId != -1) {
                contaDAO.apagarContasCliente(clienteId);
                // Atualizar a RecyclerView ou realizar outras operações após a exclusão das contas

                // Outras operações necessárias após a exclusão das contas
            }
        });
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