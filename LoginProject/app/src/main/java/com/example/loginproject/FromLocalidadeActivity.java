package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FromLocalidadeActivity extends AppCompatActivity {
    private EditText nome_localidade;
    private Button botao_salvar;

    private LocalidadeDAO localidadeDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_localidade);

        localidadeDAO = new LocalidadeDAO(this);

        nome_localidade = findViewById(R.id.nome_localidade);

        botao_salvar = findViewById(R.id.botao_salvar);


        botao_salvar.setOnClickListener(v -> {;
            salvarLocalidade();
        });

    }

    public void salvarLocalidade(){

        String localidade = nome_localidade.getText().toString();

        if(!localidade.isEmpty()){
            Localidade localidade1 = new Localidade();
            localidade1.setNome_localidade(localidade);

            localidadeDAO.SalvarLocalidade(localidade1);
        }else{
            nome_localidade.requestFocus();
            nome_localidade.setError("Informe o nome da localidade ");
        }

    }

}