package com.example.meu_repositorio.services;

import com.example.meu_repositorio.Jogador;
import com.example.meu_repositorio.repositories.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {
    private JogadorRepository jogadorRepository;

   public JogadorService(JogadorRepository jogadorRepository){
       this.jogadorRepository = jogadorRepository;
   }

public Jogador saveJogador(String nome, String sobrenome){
    Jogador novoJogador = new Jogador();
    novoJogador.setNome(nome);
    novoJogador.setSobrenome(sobrenome);
    novoJogador.setClube("criciuma");
    jogadorRepository.save(novoJogador);
    return novoJogador;
}

public List<Jogador> getAllJogadores(){
    return jogadorRepository.findAll();
}
}
