package jogo.modelo;

/**
 * Classe abstrata que representa um personagem no jogo.
 * Implementa conceitos de POO: Abstração, Encapsulamento e Polimorfismo.
 */
public abstract class Personagem {
    
    // Atributos protegidos (Encapsulamento)
    protected String nome;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int pontosAtaque;
    protected int pontosDefesa;
    protected boolean vivo;
    
    /**
     * Construtor completo com validação
     * @param nome Nome do personagem
     * @param vidaMaxima Vida máxima do personagem
     * @param pontosAtaque Pontos de ataque base
     * @param pontosDefesa Pontos de defesa base
     */
    public Personagem(String nome, int vidaMaxima, int pontosAtaque, int pontosDefesa) {
        validarNome(nome);
        validarAtributos(vidaMaxima, pontosAtaque, pontosDefesa);
        
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.pontosAtaque = pontosAtaque;
        this.pontosDefesa = pontosDefesa;
        this.vivo = true;
    }
    
    // ========== Getters (Encapsulamento) ==========
    public String getNome() { 
        return nome; 
    }
    
    public int getVidaMaxima() { 
        return vidaMaxima; 
    }
    
    public int getVidaAtual() { 
        return vidaAtual; 
    }
    
    public int getPontosAtaque() { 
        return pontosAtaque; 
    }
    
    public int getPontosDefesa() { 
        return pontosDefesa; 
    }
    
    public boolean isVivo() { 
        return vivo; 
    }
    
    public double getPorcentagemVida() {
        return (vidaAtual * 100.0) / vidaMaxima;
    }
    
    // ========== Setters com validação ==========
    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = Math.max(0, Math.min(vidaAtual, vidaMaxima));
        if (this.vidaAtual <= 0) {
            this.vivo = false;
        }
    }
    
    protected void setPontosAtaque(int pontosAtaque) {
        if (pontosAtaque < 0) {
            throw new IllegalArgumentException("Pontos de ataque não podem ser negativos");
        }
        this.pontosAtaque = pontosAtaque;
    }
    
    protected void setPontosDefesa(int pontosDefesa) {
        if (pontosDefesa < 0) {
            throw new IllegalArgumentException("Pontos de defesa não podem ser negativos");
        }
        this.pontosDefesa = pontosDefesa;
    }
    
    // ========== Métodos de Validação ==========
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do personagem não pode ser vazio");
        }
        if (nome.length() > 50) {
            throw new IllegalArgumentException("Nome muito longo (máximo 50 caracteres)");
        }
    }
    
    private void validarAtributos(int vida, int ataque, int defesa) {
        if (vida <= 0) {
            throw new IllegalArgumentException("Vida deve ser maior que zero");
        }
        if (ataque < 0 || defesa < 0) {
            throw new IllegalArgumentException("Atributos não podem ser negativos");
        }
    }
    
    // ========== Métodos de Combate ==========
    
    /**
     * Método abstrato que define o ataque (Polimorfismo)
     * Cada subclasse implementa sua própria lógica de ataque
     */
    public abstract int realizarAtaque();
    
    /**
     * Recebe dano considerando defesa
     */
    public void receberDano(int dano) {
        int danoReal = Math.max(1, dano - pontosDefesa);
        setVidaAtual(vidaAtual - danoReal);
        System.out.println(nome + " recebeu " + danoReal + " de dano!");
    }
    
    /**
     * Cura o personagem
     */
    public void curar(int quantidade) {
        if (!vivo) {
            System.out.println(nome + " está morto e não pode ser curado!");
            return;
        }
        
        int vidaAntes = vidaAtual;
        setVidaAtual(vidaAtual + quantidade);
        int vidaCurada = vidaAtual - vidaAntes;
        
        if (vidaCurada > 0) {
            System.out.println(nome + " recuperou " + vidaCurada + " pontos de vida!");
        } else {
            System.out.println(nome + " já está com vida máxima!");
        }
    }
    
    /**
     * Aumenta atributos (evolução)
     */
    public void aumentarAtributos(int vida, int ataque, int defesa) {
        this.vidaMaxima += vida;
        this.vidaAtual += vida;
        this.pontosAtaque += ataque;
        this.pontosDefesa += defesa;
    }
    
    // ========== Métodos Utilitários ==========
    
    public String getBarraVida() {
        int porcentagem = (int) getPorcentagemVida();
        int barras = porcentagem / 10;
        StringBuilder barra = new StringBuilder("[");
        
        for (int i = 0; i < 10; i++) {
            if (i < barras) {
                barra.append("█");
            } else {
                barra.append("░");
            }
        }
        barra.append("] ").append(porcentagem).append("%");
        return barra.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s | Vida: %d/%d %s | ATK: %d | DEF: %d | Status: %s",
            nome, vidaAtual, vidaMaxima, getBarraVida(), 
            pontosAtaque, pontosDefesa, vivo ? "Vivo" : "Morto");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem outro = (Personagem) obj;
        return nome.equals(outro.nome);
    }
    
    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public int atacar() {
        
        throw new UnsupportedOperationException("Unimplemented method 'atacar'");
    }

    public int defender() {
      
        throw new UnsupportedOperationException("Unimplemented method 'defender'");
    }
}