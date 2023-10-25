package com.example.loginproject.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.loginproject.database.DBHelper;

import java.util.ArrayList;
import java.util.List;
public class ClienteDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ClienteDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        //write é a variavel para escrever (inserir) e read para ler (Select)
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }
}
