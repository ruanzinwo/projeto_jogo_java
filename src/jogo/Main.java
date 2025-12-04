package jogo;

import jogo.ui.Menu;

/**
 * Classe principal que inicia o jogo
 * 
 * @author [Seu Nome]
 * @version 1.0
 */
public class Main {
    
    public static void main(String[] args) {
        
        // Configuração de encoding para caracteres especiais
        System.setProperty("file.encoding", "UTF-8");
        
        try {
            // Inicia o menu do jogo
            Menu.iniciar();
            
        } catch (Exception e) {
            System.err.println("\n❌ Erro fatal ao iniciar o jogo!");
            System.err.println("Detalhes: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}