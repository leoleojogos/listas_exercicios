package com.example.demo;

import java.util.UUID;

public class Filme {
    private UUID id;
    private String titulo;
    private String genero;
    private int capacidade;
    private int assentosOcupados;

    public Filme(UUID id, String titulo, String genero, int capacidade, int assentosOcupados) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.capacidade = capacidade;
        this.assentosOcupados = assentosOcupados;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getAssentosOcupados() {
        return assentosOcupados;
    }

    public void setAssentosOcupados(int assentosOcupados) {
        this.assentosOcupados = assentosOcupados;
    }

    public boolean temAssentosDisponiveis() {
        return assentosOcupados < capacidade;
    }

    public void ocuparAssento() {
        if (temAssentosDisponiveis()) {
            assentosOcupados++;
        } else {
            throw new IllegalStateException("Não há assentos disponíveis.");
        }
    }
}
