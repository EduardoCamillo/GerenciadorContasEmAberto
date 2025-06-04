package com.example.loginproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginproject.database.ProdutoDAO;
import com.example.loginproject.database.model.Produto;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormProdutoActivity extends AppCompatActivity {
//
    private Button btn_salvar;
    private  EditText nomeProduto;
    private  EditText valorProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nomeProduto = findViewById(R.id.nome_produto);
        valorProduto = findViewById(R.id.valor_produto);
        btn_salvar = findViewById(R.id.btn_save);
        btn_salvar.setOnClickListener(v ->{
            String conta = valorProduto.getText().toString();
            float valor_produto = Float.parseFloat(conta);
            ProdutoDAO produtoDAO = new ProdutoDAO(this);
            Produto produto = new Produto(nomeProduto.getText().toString(), valor_produto);
            produtoDAO.salvarProduto(produto);
            setResult(Activity.RESULT_OK);
            finish();
        });
    }
}