package com.example.meu_repositorio.repositories;

import com.example.meu_repositorio.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findByNomeContainingIgnoreCase(String nome);
}
