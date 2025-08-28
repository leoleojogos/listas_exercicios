package com.example;
import java.time.Year;

public class Main {

    public static void main(String[] args) {
        exercicioFatura();
        //exercicioEmpregado(); =======
        //exercicioData(); =========
    }

    // ===== EXERCÍCIO 1: Fatura =====
    static void exercicioFatura() {
        System.out.println("=== EXERCÍCIO FATURA ===");

        Fatura[] faturas = {
            new Fatura("001", "Mouse Gamer", 2, 150.0),
            new Fatura("002", "Teclado Mecânico", -3, -200.0),
            new Fatura("003", "Monitor 27\"", 1, 1200.0)
        };

        for (Fatura f : faturas) {
            System.out.println(f.getDescricao() + " | Quantidade: " + f.getQuantidade() + " | Total: R$ " + f.getTotalFatura());
        }
        System.out.println();
    }

    static class Fatura {
        private String numero;
        private String descricao;
        private int quantidade;
        private double precoPorItem;

        public Fatura(String numero, String descricao, int quantidade, double precoPorItem) {
            this.numero = numero;
            this.descricao = descricao;
            setQuantidade(quantidade);
            setPrecoPorItem(precoPorItem);
        }

        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = Math.max(0, quantidade); }

        public double getPrecoPorItem() { return precoPorItem; }
        public void setPrecoPorItem(double precoPorItem) { this.precoPorItem = Math.max(0, precoPorItem); }

        public String getDescricao() { return descricao; }
        public double getTotalFatura() { return getQuantidade() * getPrecoPorItem(); }
    }

    // ===== EXERCÍCIO 2: Empregado =====
    static void exercicioEmpregado() {
        System.out.println("=== EXERCÍCIO EMPREGADO ===");

        Empregado[] empregados = {
            new Empregado("João", "Silva", 3000),
            new Empregado("Maria", "Souza", 4500)
        };

        for (Empregado e : empregados) {
            System.out.println(e.getNome() + " " + e.getSobrenome() + " | Salário anual: R$ " + e.getSalarioAnual());
        }

        // Aplicar aumento de 10%
        for (Empregado e : empregados) {
            e.darAumento(10);
        }

        System.out.println("Após aumento de 10%:");
        for (Empregado e : empregados) {
            System.out.println(e.getNome() + " " + e.getSobrenome() + " | Salário anual: R$ " + e.getSalarioAnual());
        }
        System.out.println();
    }

    static class Empregado {
        private String nome;
        private String sobrenome;
        private double salarioMensal;

        public Empregado(String nome, String sobrenome, double salarioMensal) {
            this.nome = nome;
            this.sobrenome = sobrenome;
            setSalarioMensal(salarioMensal);
        }

        public double getSalarioMensal() { return salarioMensal; }
        public void setSalarioMensal(double salarioMensal) { this.salarioMensal = Math.max(0, salarioMensal); }

        public double getSalarioAnual() { return getSalarioMensal() * 13; }
        public void darAumento(double percentual) { setSalarioMensal(getSalarioMensal() + getSalarioMensal() * percentual / 100.0); }

        public String getNome() { return nome; }
        public String getSobrenome() { return sobrenome; }
    }

    // ===== EXERCÍCIO 3: Data =====
    static void exercicioData() {
        System.out.println("=== EXERCÍCIO DATA ===");

        Data[] datas = {
            new Data(15, 8, 2025),
            new Data(29, 2, 2024), // bissexto
            new Data(31, 11, 2023) // inválido, ajustado
        };

        for (Data d : datas) {
            d.displayData();
        }
        System.out.println();
    }

    static class Data {
        private int dia;
        private int mes;
        private int ano;

        public Data(int dia, int mes, int ano) {
            setAno(ano);
            setMes(mes);
            setDia(dia);
        }

        public void setDia(int dia) {
            int maxDias = getDiasNoMes(mes, ano);
            this.dia = (dia < 1 || dia > maxDias) ? 1 : dia;
        }

        public void setMes(int mes) {
            this.mes = (mes < 1 || mes > 12) ? 1 : mes;
        }

        public void setAno(int ano) {
            this.ano = ano;
        }

        private int getDiasNoMes(int mes, int ano) {
            switch (mes) {
                case 2:
                    return Year.isLeap(ano) ? 29 : 28;
                case 4:
                case 6:
                case 9:
                case 11:
                    return 30;
                default:
                    return 31;
            }
        }

        public void displayData() {
            System.out.println(String.format("%02d/%02d/%04d", dia, mes, ano));
        }
    }
}
