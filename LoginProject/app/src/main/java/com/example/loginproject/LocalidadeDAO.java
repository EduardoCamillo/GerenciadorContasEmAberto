package com.example.loginproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocalidadeDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public LocalidadeDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
       //write é a variavel para escrever (inserir) e read para ler (Select)
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void SalvarLocalidade(Localidade localidade){
        //classe utilizada para persistencia de dados no banco de dados (para mapear chave e valor das informações salvas)
        ContentValues cv = new ContentValues();
        cv.put("localidade", localidade.getNome_localidade());


        try{
            write.insert(DBHelper.TB_LOCALIDADE, null, cv);
            //write.close();
        }catch(Exception e){
            Log.i("ERROR", "Erro ao salvar a localidade" + e.getMessage());
        }

    }
}
