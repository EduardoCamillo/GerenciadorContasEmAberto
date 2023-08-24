package com.example.loginproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public List <Localidade> getListLocalidade(){
        List<Localidade> localidadeList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TB_LOCALIDADE + ";";

        Cursor c = read.rawQuery(sql,null);

        while (c.moveToNext()){

            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("localidade"));

            Localidade localidade = new Localidade();

            localidade.setId(id);
            localidade.setNome_localidade(nome);

            localidadeList.add(localidade);

        }

        return localidadeList;


    }

    public void atualizaLocalidade(Localidade localidade){

        ContentValues cv = new ContentValues();
        cv.put("localidade", localidade.getNome_localidade());

        //id=? significa 'ID igual alguma coisa'
        String where = "id=?";
        String[] args = {String.valueOf(localidade.getId())};

        try {
            write.update(DBHelper.TB_LOCALIDADE, cv, where, args);
            //write.close();
        }catch (Exception e ){
            Log.i("ERRADO", "erro ao atualizar o produto " + e.getMessage());
        }


    }
}
