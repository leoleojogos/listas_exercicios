package org.example;

public class Personagem {
    private String nome;
    private int vida;
    private int ataque;

    Personagem(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }

    int getVida() {
        return this.vida;
    }

    String getNome() {
        return this.nome;
    }
    void receberDano(int dano) {
        if (this.vida <= dano) {
            System.out.println("\n*" + this.nome + " foi derrotado!*");
        }
        this.vida = vida - dano;
    }

    void atacar(Personagem alvo) {
        if (alvo.vida <= 0) {
            System.out.println("O ataque falhou!");
        }
        else {
            System.out.println(alvo.nome + " sofreu " + this.ataque + " pontos de dano!");
            alvo.receberDano(this.ataque);
        }
    }
}