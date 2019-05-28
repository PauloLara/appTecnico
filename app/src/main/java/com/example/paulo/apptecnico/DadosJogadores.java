package com.example.paulo.apptecnico;


public class DadosJogadores {
    private String nomeJogador;
    private String posicaoJogador;
    private String numeroJogador;
    private String categoriaJogador;

    public DadosJogadores(String posicao, String nome, String numero, String categoria) {

        nomeJogador = nome;
        posicaoJogador = posicao;
        numeroJogador = numero;
        categoriaJogador = categoria;
    }

    public String getNomeJ() {
        return nomeJogador;
    }

    public String getPosJ() {
        return posicaoJogador;
    }

    public String getNumJ() {
        return numeroJogador;
    }

    public String getCatJ() {
        return categoriaJogador;
    }
}