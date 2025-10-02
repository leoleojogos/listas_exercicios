package com.example.meu_repositorio.repositories;

import com.example.meu_repositorio.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, UUID> {
    List<Jogador> findByNomeContainingIgnoreCase(String nome);
}
