package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loginproject.database.ClienteDAO;
import com.example.loginproject.database.model.Cliente;


public class FormClienteActivity extends AppCompatActivity {
    private EditText nome_cliente;
    private Button botao_salvar;
    private SQLiteDatabase db;
    private ClienteDAO clienteDAO;

    private Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);

        db = openOrCreateDatabase("DB_APP",MODE_PRIVATE, null);
        clienteDAO = new ClienteDAO(this);

        nome_cliente = findViewById(R.id.nome_cliente);

        botao_salvar = findViewById(R.id.botao_salvar);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            cliente = (Cliente) bundle.getSerializable("cliente");
        }


        botao_salvar.setOnClickListener(v -> {;
            salvarCliente();
        });

    }

    public void salvarCliente(){

        String cliente = nome_cliente.getText().toString();

        if(!cliente.isEmpty()){
            Toast.makeText(getApplicationContext(), "CLIENTE SALVO COM SUCESSO",Toast.LENGTH_LONG).show();
            Cliente cliente1 = new Cliente();
            cliente1.setNome_cliente(cliente);
            clienteDAO.salvarCliente(cliente1);

            setResult(Activity.RESULT_OK);
            finish();
        }else{
            nome_cliente.requestFocus();
            nome_cliente.setError("Informe o nome do ousado ");
        }

    }

}