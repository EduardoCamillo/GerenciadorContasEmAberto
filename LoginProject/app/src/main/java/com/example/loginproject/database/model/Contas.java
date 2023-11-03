package com.example.loginproject.database.model;
import java.text.SimpleDateFormat;
import android.text.format.DateFormat;
import java.util.Date;
import java.text.ParseException;
public class Contas extends Cliente {
    private int id;
    private int id_cliente;
    private float valor_compra;
    private Date data; // Adicionando o campo de data
    private String dataString;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public float getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(float valor_compra) {
        this.valor_compra = valor_compra;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data){
        this.data = data;
    }
    public String getDataString(){
        return dataString;
    }

    public void setDataString(String dataString) {
        if (dataString != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.data = sdf.parse(dataString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}