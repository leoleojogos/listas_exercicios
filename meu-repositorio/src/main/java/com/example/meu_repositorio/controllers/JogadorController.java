package com.example.meu_repositorio.controllers;

import com.example.meu_repositorio.Jogador;
import com.example.meu_repositorio.services.JogadorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    private JogadorService jogadorService;
    public JogadorController(JogadorService jogadorService){
        this.jogadorService = jogadorService;
    }
    
    @PostMapping()
    public Jogador saveJogador(@RequestBody Jogador jogador){
        return jogadorService.saveJogador(jogador.getNome(),jogador.getSobrenome());
    }
    
    @GetMapping
    public List<Jogador> getAllJogadores(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isEmpty()) {
            return jogadorService.searchByNome(nome);
        }
        return jogadorService.getAllJogadores();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable Long id) {
        Optional<Jogador> jogador = jogadorService.getJogadorById(id);
        return jogador.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJogador(@PathVariable Long id) {
        if (jogadorService.getJogadorById(id).isPresent()) {
            jogadorService.deleteJogador(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

