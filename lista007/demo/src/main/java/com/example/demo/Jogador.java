package com.example.demo;

public class Jogador {
    private String nome;
    private int posicao;
    private int idade;

    // Construtor vazio necessário para serialização/deserialização
    public Jogador() {
    }

    public Jogador(String nome, int posicao, int idade) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}