package com.example.medcontroller.model;

public class Medicamento
{
    private String id;
    private String nome;
    private String Tipo;
    private int Tamanho;
    private String Categoria;
    private String Validade;

    public Medicamento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getTamanho() {
        return Tamanho;
    }

    public void setTamanho(int tamanho) {
        Tamanho = tamanho;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getValidade() {
        return Validade;
    }

    public void setValidade(String validade) {
        Validade = validade;
    }
}
