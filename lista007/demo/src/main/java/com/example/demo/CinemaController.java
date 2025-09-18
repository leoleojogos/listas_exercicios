package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    private final List<Filme> filmes = new ArrayList<>();

    public CinemaController() {
        // Inicializa filmes
        filmes.add(new Filme(UUID.randomUUID(), "Filme A", "Ação", 100, 0));
        filmes.add(new Filme(UUID.randomUUID(), "Filme B", "Comédia", 50, 0));
    }

    @GetMapping("/filmes")
    public List<Filme> getFilmes() {
        return filmes;
    }

    @PostMapping("/filmes")
    public String criarFilme(@RequestBody Filme novoFilme) {
        filmes.add(novoFilme);
        return "Filme criado com sucesso. ID: " + novoFilme.getId();
    }

    @PatchMapping("/filmes/{id}")
    public String atualizarFilme(@PathVariable UUID id, @RequestBody Filme filmeAtualizado) {
        for (Filme filme : filmes) {
            if (filme.getId().equals(id)) {
                filme.setTitulo(filmeAtualizado.getTitulo());
                filme.setGenero(filmeAtualizado.getGenero());
                filme.setCapacidade(filmeAtualizado.getCapacidade());
                return "Filme atualizado com sucesso.";
            }
        }
        return "Filme não encontrado.";
    }

    @DeleteMapping("/filmes/{id}")
    public String deletarFilme(@PathVariable UUID id) {
        if (filmes.removeIf(filme -> filme.getId().equals(id))) {
            return "Filme removido com sucesso.";
        }
        return "Filme não encontrado.";
    }

    @PostMapping("/filmes/{id}/ingressos")
    public String comprarIngresso(@PathVariable UUID id) {
        for (Filme filme : filmes) {
            if (filme.getId().equals(id)) {
                if (filme.getAssentosOcupados() < filme.getCapacidade()) {
                    filme.setAssentosOcupados(filme.getAssentosOcupados() + 1);
                    return "Ingresso comprado com sucesso.";
                } else {
                    return "Capacidade máxima atingida.";
                }
            }
        }
        return "Filme não encontrado.";
    }

    @DeleteMapping("/filmes/{id}/ingressos")
    public String devolverIngresso(@PathVariable UUID id) {
        for (Filme filme : filmes) {
            if (filme.getId().equals(id)) {
                if (filme.getAssentosOcupados() > 0) {
                    filme.setAssentosOcupados(filme.getAssentosOcupados() - 1);
                    return "Ingresso devolvido com sucesso.";
                } else {
                    return "Nenhum ingresso para devolver.";
                }
            }
        }
        return "Filme não encontrado.";
    }
}
