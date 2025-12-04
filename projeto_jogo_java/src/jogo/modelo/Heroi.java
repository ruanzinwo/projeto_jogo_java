package jogo.modelo;

import java.util.Random;

/**
 * Classe que representa o herói (Herança e Polimorfismo)
 */
public class Heroi extends Personagem {
    
    private static final Random RANDOM = new Random();
    private int pocoesRestantes;
    private boolean esquivaAtiva;
    private int experiencia;
    private int nivel;
    
    /**
     * Construtor com valores padrão
     */
    public Heroi(String nome) {
        this(nome, 150, 25, 8);
    }
    
    /**
     * Construtor completo (Sobrecarga)
     */
    public Heroi(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
        this.pocoesRestantes = 3;
        this.esquivaAtiva = false;
        this.experiencia = 0;
        this.nivel = 1;
    }
    
    // ========== Getters e Setters ==========
    
    public int getPocoesRestantes() {
        return pocoesRestantes;
    }
    
    public boolean isEsquivaAtiva() {
        return esquivaAtiva;
    }
    
    public void setEsquivaAtiva(boolean esquivaAtiva) {
        this.esquivaAtiva = esquivaAtiva;
    }
    
    public int getExperiencia() {
        return experiencia;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    // ========== Métodos Sobrescritos (Polimorfismo) ==========
    
    @Override
    public int realizarAtaque() {
        int danoBase = pontosAtaque;
        int danoVariavel = RANDOM.nextInt(11); // 0 a 10
        int danoTotal = danoBase + danoVariavel;
        
        // Chance de crítico (20%)
        if (RANDOM.nextInt(100) < 20) {
            danoTotal *= 2;
            System.out.println("💥 ATAQUE CRÍTICO! 💥");
        }
        
        return danoTotal;
    }
    
    @Override
    public void receberDano(int dano) {
        if (esquivaAtiva) {
            System.out.println("✨ " + nome + " esquivou do ataque!");
            esquivaAtiva = false;
            return;
        }
        super.receberDano(dano);
    }
    
    // ========== Métodos Específicos do Herói ==========
    
    /**
     * Tenta esquivar do próximo ataque
     */
    public boolean tentarEsquivar() {
        boolean sucesso = RANDOM.nextInt(100) < 70; // 70% de chance
        
        if (sucesso) {
            esquivaAtiva = true;
            System.out.println("🛡️ " + nome + " preparou uma esquiva!");
        } else {
            System.out.println("❌ " + nome + " falhou ao tentar esquivar!");
        }
        
        return sucesso;
    }
    
    /**
     * Usa uma poção de cura
     */
    public void usarPocao() {
        if (pocoesRestantes <= 0) {
            System.out.println("⚠️ Você não tem mais poções!");
            return;
        }
        
        if (vidaAtual == vidaMaxima) {
            System.out.println("⚠️ Sua vida já está no máximo!");
            return;
        }
        
        pocoesRestantes--;
        int cura = vidaMaxima / 3; // Cura 33% da vida máxima
        curar(cura);
        System.out.println("🧪 Poções restantes: " + pocoesRestantes);
    }
    
    /**
     * Ataque especial mais poderoso
     */
    public int ataqueEspecial() {
        int dano = pontosAtaque * 2 + RANDOM.nextInt(16); // Dano maior
        System.out.println("⚡ ATAQUE ESPECIAL DEVASTADOR! ⚡");
        return dano;
    }
    
    /**
     * Ganha experiência e pode subir de nível
     */
    public void ganharExperiencia(int exp) {
        experiencia += exp;
        int expNecessaria = nivel * 100;
        
        if (experiencia >= expNecessaria) {
            subirNivel();
        }
    }
    
    /**
     * Aumenta os atributos ao subir de nível
     */
    private void subirNivel() {
        nivel++;
        aumentarAtributos(20, 5, 2);
        pocoesRestantes = Math.min(pocoesRestantes + 1, 5);
        
        System.out.println("\n🎉 LEVEL UP! Nível " + nivel + " alcançado! 🎉");
        System.out.println("➤ Vida máxima +20");
        System.out.println("➤ Ataque +5");
        System.out.println("➤ Defesa +2");
        System.out.println("➤ +1 Poção restaurada\n");
    }
    
    @Override
    public String toString() {
        return String.format("⚔️ %s (Nível %d) | EXP: %d | Poções: %d\n   %s",
            nome, nivel, experiencia, pocoesRestantes, super.toString());
    }

    public int atacar() {
    return realizarAtaque();
}

@Override
public int defender() {
    // Defesa = cura leve
    int cura = vidaMaxima / 10; // 10% da vida máxima
    curar(cura);
    return cura;
}

}