package org.example.ola_mundo;

import org.springframework.web.bind.annotation.*;


@RestController
public class OlaMundoController {
    @GetMapping("/")
    public String olaMundo(@RequestParam String nome){
        return "Olá,"+ nome + "!";
    }
    @PostMapping("/jogador")
    public Jogador reforcoCriciuma(@RequestBody Jogador jogadorTrasferido){
        jogadorTrasferido.setClube("Criciuma");
        return jogadorTrasferido;
    }

}

