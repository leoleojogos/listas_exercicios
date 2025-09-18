package com.example.demo;

import java.time.LocalDate;
import java.util.UUID;

public class Emprestimo {
    private UUID emprestimoId;
    private UUID livroId;
    private String usuarioId;
    private LocalDate dataEmprestimo;

    public Emprestimo(UUID emprestimoId, UUID livroId, String usuarioId, LocalDate dataEmprestimo) {
        this.emprestimoId = emprestimoId;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
    }

    public UUID getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(UUID emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public UUID getLivroId() {
        return livroId;
    }

    public void setLivroId(UUID livroId) {
        this.livroId = livroId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
}
