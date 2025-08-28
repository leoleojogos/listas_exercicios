package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        exercicio01();
    }
    static void exercicio01(){
        //criar um scanner na memoria
        Scanner leitor = new Scanner(System.in);
        // peço pro usuario digitar seu nome
        System.out.println("Digite seu nome:");
        //leio o nome e gravo numa variavel
        String nome = leitor.nextLine();
        // concateno a mensagem com a varival nome
        String mensagem = "Olá" + nome + "!";
        //mostra a mensagem final
        System.out.println(mensagem);
    }

        // Exercício 2: Dados de Funcionário
         static void exercicio02() {
            String nome = "Mariana Silva";
            String cargo = "Analista de Sistemas";
            double salario = 4500.00;

            System.out.println("Nome: " + nome);
            System.out.println("Cargo: " + cargo);
            System.out.println("Salário: R$ " + salario);
        }

        // Exercício 3: Produto
        static void exercicio03() {
            String nome = "Teclado";
            int codigo = 12345;
            double preco = 99.90;
            boolean promocao = true;

            System.out.println("Produto: " + nome);
            System.out.println("Código: " + codigo);
            System.out.println("Preço: R$ " + preco);
            System.out.println("Promoção: " + promocao);
        }

        // Exercício 4: Desconto
            static void exercicio04() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite o valor da compra: R$ ");
            double valorCompra = sc.nextDouble();

            if (valorCompra > 100) {
                double desconto = valorCompra * 0.10;
                double valorFinal = valorCompra - desconto;
                System.out.printf("Valor final com desconto: R$ %.2f\n", valorFinal);
            } else {
                System.out.printf("Valor final sem desconto: R$ %.2f\n", valorCompra);
            }

            sc.close();
        }

        // Exercício 5: Categoria por Idade
        static void exercicio05() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite sua idade: ");
            int idade = sc.nextInt();

            if (idade < 12) {
                System.out.println("Categoria: Infantil");
            } else if (idade >= 12 && idade <= 17) {
                System.out.println("Categoria: Adolescente");
            } else {
                System.out.println("Categoria: Adulto");
            }

            sc.close();
        }

        // Exercício 6: Notas dos Alunos
         static void exercicio06() {
            int[] notas = {7, 8, 6, 9, 10};
            double soma = 0;

            System.out.print("Notas: ");
            for (int i = 0; i < notas.length; i++) {
                System.out.print(notas[i] + " ");
                soma += notas[i];
            }

            double media = soma / notas.length;
            System.out.printf("\nMédia: %.1f\n", media);
        }

        // Exercício 7: Calcular IMC
       static void exercicio07() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite seu peso (kg): ");
            double peso = sc.nextDouble();

            System.out.print("Digite sua altura (m): ");
            double altura = sc.nextDouble();

            double imc = calcularIMC(peso, altura);
            System.out.printf("IMC calculado: %.2f\n", imc);

            sc.close();
        }

        // Método para calcular o IMC
        public static double calcularIMC(double peso, double altura) {
            return peso / (altura * altura);
        }

        // Exercício 8: Trabalhando com Strings
        public static void exercicio08() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite seu nome completo: ");
            String nome = sc.nextLine();

            int numCaracteres = nome.replace(" ", "").length();
            String nomeMaiusculas = nome.toUpperCase();
            boolean contemSilva = nome.contains("Silva");

            System.out.println("Número de caracteres: " + numCaracteres);
            System.out.println("Maiúsculas: " + nomeMaiusculas);
            System.out.println("Contém \"Silva\": " + contemSilva);

            sc.close();
    }
}

