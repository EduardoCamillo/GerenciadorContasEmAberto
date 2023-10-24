package com.example.loginproject;

import android.app.LocaleConfig;

import com.example.loginproject.database.model.Localidade;

import java.util.Date;

public class Cliente extends Localidade {

    private int id_localidade;
    private String nome_cliente;


    public Cliente(int id_localidade, String nome_cliente) {
        super.id = id_localidade;
        this.nome_cliente = nome_cliente;

    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }
}
