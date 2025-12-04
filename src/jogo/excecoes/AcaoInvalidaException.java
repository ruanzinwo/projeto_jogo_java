package jogo.excecoes;

/**
 * Exceção customizada para ações inválidas no jogo
 */
public class AcaoInvalidaException extends Exception {
    
    private String acao;
    
    public AcaoInvalidaException(String mensagem) {
        super(mensagem);
    }
    
    public AcaoInvalidaException(String mensagem, String acao) {
        super(mensagem);
        this.acao = acao;
    }
    
    public AcaoInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
    public String getAcao() {
        return acao;
    }
    
    @Override
    public String getMessage() {
        if (acao != null) {
            return super.getMessage() + " | Ação: " + acao;
        }
        return super.getMessage();
    }
}