package com.example.loginproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.loginproject.database.model.Produto;

public class ProdutoDAO {
    private final SQLiteDatabase write;
    private final SQLiteDatabase read;


    public ProdutoDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();

        dbHelper.onUpgrade(write, 0, 1);
    }
    public void salvarProduto(Produto produto){
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("valor", produto.getValor());

        try{
            write.insert(DBHelper.TB_PRODUTO, null, cv);
            //write.close();
        }catch(Exception e){
            Log.i("ERROR", "Erro ao salvar o PRODUTO" + e.getMessage());
        }
    }
}
