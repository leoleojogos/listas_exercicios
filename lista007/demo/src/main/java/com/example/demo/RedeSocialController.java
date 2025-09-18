package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/rede-social")
public class RedeSocialController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final Map<UUID, List<Tweet>> tweetsPorUsuario = new HashMap<>();

    public RedeSocialController() {
        // Inicializa usuários
        for (int i = 1; i <= 3; i++) {
            UUID userId = UUID.randomUUID();
            usuarios.add(new Usuario(userId, "Usuario " + i, "usuario" + i + "@email.com"));
            tweetsPorUsuario.put(userId, new ArrayList<>());
        }
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @GetMapping("/usuarios/{id}/tweets")
    public List<Tweet> getTweets(@PathVariable UUID id) {
        return tweetsPorUsuario.getOrDefault(id, Collections.emptyList());
    }

    @PostMapping("/usuarios/{id}/tweets")
    public String criarTweet(@PathVariable UUID id, @RequestBody String mensagem) {
        List<Tweet> tweets = tweetsPorUsuario.get(id);
        if (tweets == null) {
            return "Usuário não encontrado.";
        }
        Tweet tweet = new Tweet(UUID.randomUUID(), mensagem, false, LocalDateTime.now());
        tweets.add(tweet);
        return "Tweet criado com sucesso. ID: " + tweet.getTweetId();
    }

    @PatchMapping("/usuarios/{id}/tweets/{tweetId}")
    public String atualizarTweet(@PathVariable UUID id, @PathVariable UUID tweetId, @RequestBody String novaMensagem) {
        List<Tweet> tweets = tweetsPorUsuario.get(id);
        if (tweets == null) {
            return "Usuário não encontrado.";
        }
        for (Tweet tweet : tweets) {
            if (tweet.getTweetId().equals(tweetId)) {
                tweet.setMensagem(novaMensagem);
                tweet.setEditado(true);
                return "Tweet atualizado com sucesso.";
            }
        }
        return "Tweet não encontrado.";
    }

    @DeleteMapping("/usuarios/{id}/tweets/{tweetId}")
    public String deletarTweet(@PathVariable UUID id, @PathVariable UUID tweetId) {
        List<Tweet> tweets = tweetsPorUsuario.get(id);
        if (tweets == null) {
            return "Usuário não encontrado.";
        }
        if (tweets.removeIf(tweet -> tweet.getTweetId().equals(tweetId))) {
            return "Tweet removido com sucesso.";
        }
        return "Tweet não encontrado.";
    }
}
