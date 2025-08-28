package com.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // aqui você escolhe qual exercício rodar
        exercicio01();
        // exercicio02();
        // exercicio03();
    }

    // Exercício 1 – Gerador de Senha
    static void exercicio01() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder senha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(index));
        }

        System.out.println("Senha gerada: " + senha);
    }

    // Exercício 2 – Gerador de Jogador
    static void exercicio02() {
        String[] nomes = {"Carlos", "João", "Pedro", "Rafael", "Lucas"};
        String[] sobrenomes = {"Silva", "Souza", "Pereira", "Costa", "Oliveira"};
        String[] posicoes = {"goleiro", "zagueiro", "meia", "atacante", "lateral"};
        String[] times = {"Corinthians", "Palmeiras", "São Paulo", "Flamengo", "Grêmio"};

        Random random = new Random();

        String nome = nomes[random.nextInt(nomes.length)];
        String sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
        String posicao = posicoes[random.nextInt(posicoes.length)];
        String time = times[random.nextInt(times.length)];
        int idade = 18 + random.nextInt(23); // idade entre 18 e 40

        System.out.println(nome + " " + sobrenome + " é um futebolista brasileiro de "
                + idade + " anos que atua como " + posicao + ". Atualmente defende o " + time + ".");
    }

    // Exercício 3 – Gerador de Pokémon
    static void exercicio03() {
        String[] nomes = {"Pikachu", "Charmander", "Squirtle", "Bulbasaur", "Eevee", "Snorlax"};
        String[] tipos = {"Elétrico", "Fogo", "Água", "Planta", "Normal", "Psíquico"};

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Quantos Pokémon deseja gerar? ");
        int quantidade = scanner.nextInt();

        for (int i = 0; i < quantidade; i++) {
            String nome = nomes[random.nextInt(nomes.length)];
            String tipo = tipos[random.nextInt(tipos.length)];
            int nivel = 1 + random.nextInt(100); // nível entre 1 e 100

            System.out.println(nome + " é um Pokémon do tipo " + tipo + " de nível " + nivel + ".");
        }
    }
}
