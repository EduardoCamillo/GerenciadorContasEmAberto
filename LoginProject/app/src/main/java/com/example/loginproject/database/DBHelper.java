package com.example.loginproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_DB = "DB_APP";
    public static final String TB_LOCALIDADE = "TB_LOCALIDADE";
    public static final String TB_CLIENTE = "TB_CLIENTE";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //EXECUTADO SEMPRE QUANDO O APP FOR CRIAR O BANCO DE DADOS PELA PRIMEIRA VEZ
        String sqlLocalidade = "CREATE TABLE IF NOT EXISTS " + TB_LOCALIDADE
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " localidade TEXT NOT NULL); ";

        String sqlCliente = "CREATE TABLE IF NOT EXISTS " + TB_CLIENTE
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " idLocalidade INTEGER, " +
                " FOREIGN KEY (idLocalidade) REFERENCES " + TB_LOCALIDADE + " (id));";
        try {
            sqLiteDatabase.execSQL(sqlLocalidade);
            sqLiteDatabase.execSQL(sqlCliente);
        }catch(Exception e){
            Log.i("ERRADO", "Deu ruim ao criar a tabela duzao");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //EXECUTADO SEMPRE QUANDO ATUALIZARMOS A VERSÃO DO BANCO DE DADOS
    }
}
