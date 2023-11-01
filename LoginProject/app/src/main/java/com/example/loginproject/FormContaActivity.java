package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loginproject.database.ClienteDAO;
import com.example.loginproject.database.ContaDAO;
import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Contas;
import android.os.Bundle;

public class FormContaActivity extends AppCompatActivity {
    private EditText valor_conta;
    private Button botao_salvar;
    private DatePicker data;
    private SQLiteDatabase db;
    private ContaDAO contaDAO;
    private int clienteId;
    private  Contas conta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_conta);


        clienteId = getIntent().getIntExtra("clienteId", -1);
        db = openOrCreateDatabase("DB_APP",MODE_PRIVATE, null);

        contaDAO = new ContaDAO(this);

        valor_conta = findViewById(R.id.valor);
        botao_salvar = findViewById(R.id.botao_salvar);
        data = findViewById(R.id.data);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            conta = (Contas) bundle.getSerializable("conta");
        }

        botao_salvar.setOnClickListener(v -> {;
            salvarConta();
        });





    }
    public void salvarConta(){

        String conta = valor_conta.getText().toString();

        if(!conta.isEmpty()){
            float ValorConta = Float.parseFloat(conta);
            // Obter a data selecionada do DatePicker
            int day = data.getDayOfMonth();
            int month = data.getMonth();
            int year = data.getYear();

            // Aqui você pode usar a data selecionada para fazer o que deseja, como salvá-la no objeto de Contas
            String dataCompra = day + "-" + (month + 1) + "-" + year; // Formatando a data





            Toast.makeText(getApplicationContext(), "COMPRA SALVA COM SUCESSO",Toast.LENGTH_LONG).show();
            Contas conta1 = new Contas();
            conta1.setValor_compra(ValorConta);
            conta1.setId_cliente(clienteId);
            contaDAO.salvarConta(conta1);

            setResult(Activity.RESULT_OK);
            finish();
        }else{
            valor_conta.requestFocus();
            valor_conta.setError("Digita a conta ai parcerao ");
        }

    }
}