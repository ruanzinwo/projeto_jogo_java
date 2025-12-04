package jogo.excecoes;

/**
 * Exceção para recursos insuficientes (poções, etc)
 */
public class RecursosInsuficientesException extends Exception {
    
    private String recurso;
    private int quantidadeAtual;
    
    public RecursosInsuficientesException(String recurso, int quantidadeAtual) {
        super(String.format("Recursos insuficientes! %s disponível: %d", recurso, quantidadeAtual));
        this.recurso = recurso;
        this.quantidadeAtual = quantidadeAtual;
    }
    
    public String getRecurso() {
        return recurso;
    }
    
    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }
}