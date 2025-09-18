package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    private final List<Livro> livrosDisponiveis = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public BibliotecaController() {
        // Inicializa livros disponíveis
        livrosDisponiveis.add(new Livro(UUID.randomUUID(), "Livro A", "Autor A"));
        livrosDisponiveis.add(new Livro(UUID.randomUUID(), "Livro B", "Autor B"));
        livrosDisponiveis.add(new Livro(UUID.randomUUID(), "Livro C", "Autor C"));
    }

    @GetMapping("/livros")
    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    @GetMapping("/emprestados")
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    @PostMapping("/emprestados")
    public String criarEmprestimo(@RequestParam UUID livroId, @RequestParam String usuarioId) {
        Livro livro = livrosDisponiveis.stream()
                .filter(l -> l.getId().equals(livroId))
                .findFirst()
                .orElse(null);

        if (livro == null) {
            return "Livro não encontrado.";
        }

        livrosDisponiveis.remove(livro);
        Emprestimo emprestimo = new Emprestimo(UUID.randomUUID(), livroId, usuarioId, LocalDate.now());
        emprestimos.add(emprestimo);

        return "Empréstimo criado com sucesso. ID: " + emprestimo.getEmprestimoId();
    }

    @DeleteMapping("/emprestados/{emprestimoId}")
    public String devolverEmprestimo(@PathVariable UUID emprestimoId) {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getEmprestimoId().equals(emprestimoId))
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            return "Empréstimo não encontrado.";
        }

        emprestimos.remove(emprestimo);
        Livro livro = new Livro(emprestimo.getLivroId(), "Título Desconhecido", "Autor Desconhecido");
        livrosDisponiveis.add(livro);

        return "Empréstimo devolvido com sucesso.";
    }
}
