package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();

        List<Personagem> inimigos = new ArrayList<Personagem>();

        inimigos.add(new Personagem("Org", 100, 10));
        inimigos.add(new Personagem("Globin", 50, 5));
        inimigos.add(new Personagem("Lobo", 75, 15));
        inimigos.add(new Personagem("Cavaleiro", 200, 20));
        inimigos.add(new Personagem("Dragão", 500, 50));

        Personagem jogador = new Personagem("Steve", 100, 20);

        int round = 1;

        while(jogador.getVida() > 0 || !inimigos.isEmpty()) {

            System.out.println("\nRound n° " + round + "\n-----------------------------");
            int alvoAleatorio = r.nextInt(inimigos.size());
            jogador.atacar(inimigos.get(alvoAleatorio));

            if (inimigos.get(alvoAleatorio).getVida() <= 0) {
                inimigos.remove(alvoAleatorio);
            }

            System.out.println(" ");

            for (int i = 0; i < inimigos.size(); i++) {
                inimigos.get(i).atacar(jogador);
            }
            TimeUnit.SECONDS.sleep(4);

            if (inimigos.isEmpty()) {
                System.out.println("\n=============================\n" + jogador.getNome() + " venceu!\n=============================");
                break;
            }
            if (jogador.getVida() <= 0) {
                System.out.println("\n=============================\n" + jogador.getNome() + " perdeu!\n=============================");
                break;
            }
            round++;

        }




    };
}