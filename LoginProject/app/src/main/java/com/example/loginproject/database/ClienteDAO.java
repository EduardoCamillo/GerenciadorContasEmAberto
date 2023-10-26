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
import java.util.List;
public class ClienteDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ClienteDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        //write é a variavel para escrever (inserir) e read para ler (Select)
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();

        dbHelper.onUpgrade(write, 0, 1);
    }
    public void salvarCliente(Cliente cliente){
        //classe utilizada para persistencia de dados no banco de dados (para mapear chave e valor das informações salvas)
        ContentValues cv = new ContentValues();
        cv.put("Cliente: ", cliente.getNome_cliente());


        try{
            write.insert(DBHelper.TB_CLIENTE, null, cv);
            //write.close();
        }catch(Exception e){
            Log.i("ERROR", "Erro ao salvar a cliente" + e.getMessage());
        }

    }
    public List <Cliente> getListCliente() {
        List<Cliente> clienteList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TB_CLIENTE + ";";

        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {

            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));

            Cliente cliente = new Cliente();

            cliente.setId(id);
            cliente.setNome_cliente(nome);

            clienteList.add(cliente);

        }
        return clienteList;
    }
}
