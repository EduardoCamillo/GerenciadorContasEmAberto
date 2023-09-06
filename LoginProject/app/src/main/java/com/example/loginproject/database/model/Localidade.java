package com.example.loginproject.database.model;

import java.io.Serializable;

public class Localidade implements Serializable {
    private String nome_localidade;

    private int id;

    public String getNome_localidade() {
        return nome_localidade;
    }

    public void setNome_localidade(String nome_localidade) {
        this.nome_localidade = nome_localidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
