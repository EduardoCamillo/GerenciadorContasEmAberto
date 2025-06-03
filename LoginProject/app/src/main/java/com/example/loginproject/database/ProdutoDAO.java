package com.example.loginproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

    }
}
