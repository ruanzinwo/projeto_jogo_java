package jogo.excecoes;

/**
 * Exceção para quando tenta usar personagem morto
 */
public class PersonagemMortoException extends Exception {
    
    public PersonagemMortoException(String nomePersonagem) {
        super("O personagem " + nomePersonagem + " está morto e não pode realizar ações!");
    }
}