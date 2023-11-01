package com.example.loginproject.database.model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contas extends Cliente{
    private int id;
    private int id_cliente;
    private float valor_compra;
    private String data_compra;

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
    public void setValor_compra(float valor_compra){
        this.valor_compra = valor_compra;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
