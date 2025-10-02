package com.example.meu_repositorio.controllers;

import com.example.meu_repositorio.Jogador;
import com.example.meu_repositorio.services.JogadorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Jogador>getAllJogadores(){
        return jogadorService.getAllJogadores();
    }
}

