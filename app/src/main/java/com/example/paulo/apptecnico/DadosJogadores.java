package com.example.paulo.apptecnico;


public class DadosJogadores {
    private String posicaoJogador;
    private String nomeJogador;
    private String numeroJogador;

    public DadosJogadores(String posicao, String nome, String numero) {

        posicaoJogador = posicao;
        nomeJogador = nome;
        numeroJogador = numero;
    }

    public String getPosJ() {
        return posicaoJogador;
    }

    public String getNomeJ() {
        return nomeJogador;
    }

    public String getNumJ() {
        return numeroJogador;
    }
}