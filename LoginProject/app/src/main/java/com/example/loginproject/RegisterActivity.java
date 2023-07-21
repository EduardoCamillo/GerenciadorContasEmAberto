package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;


public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;
    private EditText edit_email;
    private EditText edit_senha;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_delete = findViewById(R.id.btn_delete);
        btn_register = findViewById(R.id.btn_register);
        edit_senha = findViewById(R.id.edit_senha);
        edit_email = findViewById(R.id.edit_email);
        //criando o banco
        SQLiteDatabase bd = openOrCreateDatabase("USERS", MODE_PRIVATE, null);
        //criando tabela
        bd.execSQL("CREATE TABLE IF NOT EXISTS user(email VARCHAR, senha VARCHAR)");


        btn_register.setOnClickListener(v -> {
            String emailE = edit_email.getText().toString();
            String senhaP = edit_senha.getText().toString();

            if (emailE.equals("") || senhaP.equals("")) {
                Toast.makeText(getApplicationContext(), "INSIRA UM E-MAIL E SENHA", Toast.LENGTH_LONG).show();
            } else {
                try {

                    //insert
                    bd.execSQL("INSERT INTO user(email, senha) VALUES ('" + emailE + "', '" + senhaP + "');");

                    Cursor cursor = bd.rawQuery("SELECT email,senha FROM user;", null);


                    int indiceEmail = cursor.getColumnIndex("email");
                    int indiceSenha = cursor.getColumnIndex("senha");

                    Toast.makeText(getApplicationContext(), "USUÁRIO CADASTRADO COM SUCESSO",Toast.LENGTH_LONG).show();
                    cursor.moveToFirst();
                    while (cursor != null) {
                        Log.i("RESULTADO - email", cursor.getString(indiceEmail));
                        Log.i("RESULTADO - senha", cursor.getString(indiceSenha));
                        cursor.moveToNext();
                    }
                } catch (Exception e) {
                    Log.d("DEU_ERRO", "SE FUDEU KKKKKKKK");
                    e.printStackTrace();
                }

            }

        });

        btn_delete.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Registros apagados", Toast.LENGTH_SHORT).show();
            bd.execSQL("DELETE FROM user;");


        });

    }
}