package com.example.loginproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String NOME_DB = "DB_APP";
    private static final String TB_USERS = "USERS";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    /*public void insertData (String email, String senha){
        SQLiteDatabase sql = null;
        sql.execSQL("INSERT INTO TB_USERS VALUES('email', 'senha');");
    }
*/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_USERS
                + "(email VARCHAR(200) NOT NULL, " +
                "senha VARCHAR(200) NOT NULL); ";

        try{
            sqLiteDatabase.execSQL(sql);
            Log.d("CEPA FOI", "foi mesmo mlk");

        }catch (Exception e){
            Log.d("ERROR", "Erro ao criar a tabela: " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
