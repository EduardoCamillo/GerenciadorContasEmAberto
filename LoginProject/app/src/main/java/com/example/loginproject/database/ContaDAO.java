package com.example.loginproject.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.loginproject.database.DBHelper;
import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Localidade;

import java.util.ArrayList;
public class ContaDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ContaDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        //write é a variavel para escrever (inserir) e read para ler (Select)
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();

        dbHelper.onUpgrade(write, 0, 1);
    }


}
