package com.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // exercicio01(); // gera senhas
        exercicio02(); // gera jogadores
        // exercicio03(); // gera pokémons
    }

    // ======== EXERCÍCIO 1: GERADOR DE SENHAS =========
    static void exercicio01() {
        System.out.println("\n--- GERADOR DE SENHAS ---");

        for (int i = 0; i < 10; i++) { // gera 10 senhas
            Senha senha = new Senha();
            System.out.println("Senha " + (i + 1) + ": " + senha.getSenha());
        }
    }

    // ======== EXERCÍCIO 2: GERADOR DE JOGADORES =========
    static void exercicio02() {
        System.out.println("\n--- GERADOR DE JOGADORES ---");

        for (int i = 0; i < 11; i++) { // gera 11 jogadores
            Jogador jogador = new Jogador();
            System.out.println("Jogador " + (i + 1) + ": " + jogador);
        }
    }

    // ======== EXERCÍCIO 3: GERADOR DE POKÉMONS =========
    static void exercicio03() {
        System.out.println("\n--- GERADOR DE POKÉMONS ---");
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos Pokémons deseja gerar? ");
        int qtd = sc.nextInt();

        for (int i = 0; i < qtd; i++) {
            Pokemon pokemon = new Pokemon();
            System.out.println("Pokémon " + (i + 1) + ": " + pokemon);
        }
    }
}

// =================== CLASSES ===================

// Classe para gerar senhas
class Senha {
    private String senha;
    private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public Senha() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        this.senha = sb.toString();
    }

    public String getSenha() {
        return senha;
    }
}

// Classe Jogador
class Jogador {
    private String nome;
    private String sobrenome;
    private String posicao;
    private String time;
    private int idade;

    private static final String[] nomes = {"Carlos", "João", "Pedro", "Rafael", "Lucas"};
    private static final String[] sobrenomes = {"Silva", "Souza", "Pereira", "Costa", "Oliveira"};
    private static final String[] posicoes = {"goleiro", "zagueiro", "meia", "atacante", "lateral"};
    private static final String[] times = {"Corinthians", "Palmeiras", "São Paulo", "Flamengo", "Grêmio"};

    private static final Random random = new Random();

    public Jogador() {
        this.nome = nomes[random.nextInt(nomes.length)];
        this.sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
        this.posicao = posicoes[random.nextInt(posicoes.length)];
        this.time = times[random.nextInt(times.length)];
        this.idade = 18 + random.nextInt(23); // 18 até 40
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome + " é um futebolista brasileiro de "
                + idade + " anos que atua como " + posicao
                + ". Atualmente defende o " + time + ".";
    }
}

// Classe Pokemon
class Pokemon {
    private String nome;
    private String tipo;
    private int nivel;

    private static final String[] nomes = {"Pikachu", "Charmander", "Squirtle", "Bulbasaur", "Eevee", "Snorlax"};
    private static final String[] tipos = {"Elétrico", "Fogo", "Água", "Planta", "Normal", "Psíquico"};

    private static final Random random = new Random();

    public Pokemon() {
        this.nome = nomes[random.nextInt(nomes.length)];
        this.tipo = tipos[random.nextInt(tipos.length)];
        this.nivel = 1 + random.nextInt(100); // nível entre 1 e 100
    }

    @Override
    public String toString() {
        return nome + " é um Pokémon do tipo " + tipo + " de nível " + nivel + ".";
    }
}
