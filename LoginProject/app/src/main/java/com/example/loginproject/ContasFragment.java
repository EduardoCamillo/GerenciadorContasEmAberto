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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ContasFragment extends Fragment {

    private ClientesAdapter clientesAdapter;
    private ClienteDAO clienteDAO;
    private AdapterClickListener adapterClickListener;
    private int clienteId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contas, container, false);

        clienteId = getArguments().getInt("cliente_id", -1);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());







    return view;
    }
}