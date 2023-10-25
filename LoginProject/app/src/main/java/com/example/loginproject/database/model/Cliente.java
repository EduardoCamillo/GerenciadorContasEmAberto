package com.example.loginproject.database.model;

import android.app.LocaleConfig;

import com.example.loginproject.database.model.Localidade;

import java.util.Date;

public class Cliente extends Localidade {

    private int id;
    private int id_localidade;
    private String nome_cliente;


    public Cliente(int id_localidade,int id, String nome_cliente) {
        super.id = id_localidade;
        this.id = id;
        this.nome_cliente = nome_cliente;

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getNomeCliente() {
        return nome_cliente;
    }
}