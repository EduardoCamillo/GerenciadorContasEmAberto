package com.example.loginproject;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginproject.database.LocalidadeDAO;
import com.example.loginproject.database.model.Localidade;

public class FormLocalidadeActivity extends AppCompatActivity {
    private EditText nome_localidade;
    private Button botao_salvar;
    private SQLiteDatabase db;
    private LocalidadeDAO localidadeDAO;

    private Localidade localidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_localidade);

        db = openOrCreateDatabase("DB_APP",MODE_PRIVATE, null);
        localidadeDAO = new LocalidadeDAO(this);

        nome_localidade = findViewById(R.id.nome_localidade);

        botao_salvar = findViewById(R.id.botao_salvar);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            localidade = (Localidade) bundle.getSerializable("localidade");
        }


        botao_salvar.setOnClickListener(v -> {;
            Toast.makeText(getApplicationContext(), "LOCALIDADE SALVA COM SUCESSO",Toast.LENGTH_LONG).show();
            salvarLocalidade();
        });

    }

    public void salvarLocalidade(){

        String localidade = nome_localidade.getText().toString();

        if(!localidade.isEmpty()){
            Localidade localidade1 = new Localidade();
            localidade1.setNome_localidade(localidade);
            localidadeDAO.salvarLocalidade(localidade1);

            setResult(Activity.RESULT_OK);
            finish();
        }else{
            nome_localidade.requestFocus();
            nome_localidade.setError("Informe o nome da localidade ");
        }

    }

}