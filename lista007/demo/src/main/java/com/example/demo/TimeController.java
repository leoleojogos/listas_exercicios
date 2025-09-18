package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {

    private final List<Jogador> jogadores = new ArrayList<>();

    public TimeController() {
        // Inicializa os titulares (0-10) e reservas (11-15)
        for (int i = 0; i < 11; i++) {
            jogadores.add(new Jogador("Titular " + i, i, 20 + i));
        }
        for (int i = 11; i < 16; i++) {
            jogadores.add(new Jogador("Reserva " + i, i, 20 + i));
        }
    }

    @GetMapping("/principal")
    public List<Jogador> getTitulares() {
        return jogadores.subList(0, 11); // Retorna os titulares (0-10)
    }

    @GetMapping("/reservas")
    public List<Jogador> getReservas() {
        return jogadores.subList(11, jogadores.size()); // Retorna os reservas (11-15)
    }

    @PutMapping("/jogador/{posicao}")
    public String substituirJogador(@PathVariable int posicao, @RequestParam int reservaIndex) {
        if (posicao < 0 || posicao > 10) {
            return "Posição inválida. Deve estar entre 0 e 10.";
        }
        if (reservaIndex < 11 || reservaIndex >= jogadores.size()) {
            return "Índice de reserva inválido. Deve estar entre 11 e 15.";
        }

        Jogador reserva = jogadores.get(reservaIndex);
        Jogador substituido = jogadores.set(posicao, reserva);
        jogadores.set(reservaIndex, substituido);

        return "Jogador " + substituido.getNome() + " foi substituído por " + reserva.getNome() + ".";
    }

    @PostMapping("/jogadores")
    public String cadastrarJogador(@RequestBody Jogador novoJogador) {
        if (novoJogador.getPosicao() < 0 || novoJogador.getPosicao() >= jogadores.size()) {
            return "Posição inválida. Deve estar entre 0 e " + (jogadores.size() - 1) + ".";
        }
        jogadores.add(novoJogador);
        return "Jogador " + novoJogador.getNome() + " cadastrado com sucesso.";
    }
}
