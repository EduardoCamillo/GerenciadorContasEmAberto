package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.database.Cursor;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FormLogin extends AppCompatActivity{// implements View.OnClickListener {

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    public ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        this.mViewHolder.edit_email = findViewById(R.id.edit_email);
        this.mViewHolder.edit_senha = findViewById(R.id.edit_senha);
        this.mViewHolder.botaum = findViewById(R.id.botaum);
        this.mViewHolder.botaum2 = findViewById(R.id.botaum2);


        mViewHolder.botaum.setOnClickListener(v -> {


            String email = this.mViewHolder.edit_email.getText().toString();
            String password = this.mViewHolder.edit_senha.getText().toString();
            SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0 );
            SharedPreferences.Editor editor = preferences.edit();
            SQLiteDatabase db = openOrCreateDatabase("USERS", MODE_PRIVATE, null);;

            if(email.equals("") || password.equals("")){
                Toast.makeText(getApplicationContext(), "Insira um email ou senha!", Toast.LENGTH_LONG).show();
            }else{
               /* editor.putString("email", email);
                //commit salva
                editor.commit();
                mostrar.setText("Seu e-mail é: " + email);
                */
                try{
                    Cursor cursor = db.rawQuery("SELECT email,senha FROM user WHERE email = '" + email + "' AND senha = '" + password + "';", null);

                    int indiceEmail = cursor.getColumnIndex("email");
                    int indiceSenha = cursor.getColumnIndex("senha");


                    if (cursor != null && cursor.moveToFirst()) {
                            startActivity(new Intent(this, MainActivity.class));
                            Log.d("FOI", "é amigão talvez foi");
                        }else{
                        Toast.makeText(getApplicationContext(), "Email ou senha inválidos!", Toast.LENGTH_LONG).show();
                        }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0 );


        mViewHolder.botaum2.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));


        });
    }

    private static class ViewHolder {
        Button botaum2;
        Button botaum;
        EditText edit_email;
        EditText edit_senha;
    }
}






