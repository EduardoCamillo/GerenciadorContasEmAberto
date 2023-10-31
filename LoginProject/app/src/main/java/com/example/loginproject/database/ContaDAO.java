package com.example.loginproject.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.loginproject.database.model.Contas;
import com.example.loginproject.database.DBHelper;
import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Localidade;

import java.util.ArrayList;
import java.util.List;

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

    public void salvarConta(Contas conta) {
        //classe utilizada para persistencia de dados no banco de dados (para mapear chave e valor das informações salvas)
        ContentValues cv = new ContentValues();
        cv.put("valor", conta.getValor_compra());
        cv.put("idCliente", conta.getId_cliente());

        try {
            write.insert(DBHelper.TB_CONTA, null, cv);
            //write.close();
        } catch (Exception e) {
            Log.i("ERROR", "Erro ao salvar a CONTA" + e.getMessage());
        }

    }
    public List<Contas> getContasCliente(int clienteid) {
        List<Contas> listaDeContas = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TB_CONTA + " WHERE idCliente = ?";
        Cursor cursor = read.rawQuery(sql, new String[]{String.valueOf(clienteid)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            float valor = cursor.getFloat(cursor.getColumnIndex("valor"));
            int idCliente = cursor.getInt(cursor.getColumnIndex("idCliente")); // Recupere o ID da localidade


            Contas conta = new Contas();
            conta.setId(id);
            conta.setValor_compra(valor);
            conta.setId_cliente(idCliente);
            listaDeContas.add(conta);
        }

        cursor.close();
        return listaDeContas;
    }


}
