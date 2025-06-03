package com.example.loginproject.database.model;

public class Produto {

    public int id;
    public String nome;
    public float valor;


    public Produto(String nome, float valor){
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome(){
        return this.nome;
    }
    public float getValor(){
        return this.valor;
    }

}
