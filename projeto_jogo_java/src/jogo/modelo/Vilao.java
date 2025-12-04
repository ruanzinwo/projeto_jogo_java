package jogo.modelo;

import java.util.Random;

public class Vilao extends Personagem {

    private static final Random random = new Random();

    public Vilao(String nome, int vida, int ataque) {
        super(nome, vida, ataque, ataque);
    }

    public static Vilao gerarVilaoAleatorio() {
        String[] nomes = {"Goblin", "Orc", "Esqueleto", "Zumbi", "Troll"};
        String nome = nomes[random.nextInt(nomes.length)];

        int vida = 40 + random.nextInt(40);  // 40–80 HP
        int ataque = 5 + random.nextInt(10); // 5–15 ATK

        return new Vilao(nome, vida, ataque);
    }

    
    @Override
    public int realizarAtaque() {
        return getPontosAtaque() + random.nextInt(6); // dano extra 0–5
    }

    
    public int atacar() {
        return realizarAtaque();
    }
}

