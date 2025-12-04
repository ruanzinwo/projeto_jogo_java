package jogo.ui;

import jogo.dados.BancoDados;
import jogo.excecoes.*;
import jogo.modelo.*;
import java.util.*;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static BancoDados bancoDados;

    public static void iniciar() {
        bancoDados = new BancoDados();
        
        try {
            exibirTitulo();
            Heroi heroi = criarHeroi();
            
            boolean continuar = true;
            while (continuar && heroi.isVivo()) {
                continuar = menuPrincipal(heroi);
            }

        } catch (Exception e) {
            System.err.println("\n❌ Erro crítico: " + e.getMessage());
            e.printStackTrace();

        } finally {
            bancoDados.fechar();
            scanner.close();
        }
    }

    // ----------------------------------------
    // TÍTULO DO JOGO
    // ----------------------------------------
    private static void exibirTitulo() {
        System.out.println("""
            ╔═══════════════════════════════════════════╗
            ║                                           ║
            ║     ⚔️  JOGO DE BATALHA ÉPICO  ⚔️        ║
            ║                                           ║
            ║     Sistema POO Completo + Banco de Dados ║
            ║                                           ║
            ╚═══════════════════════════════════════════╝
            """);
    }

    // ----------------------------------------
    // LIMPAR TELA (FUNCIONANDO)
    // ----------------------------------------
    private static void limparTela() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

        } catch (Exception e) {
            // Se falhar, printa linhas em branco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    // ----------------------------------------
    // CRIAÇÃO DO HERÓI
    // ----------------------------------------
    private static Heroi criarHeroi() {
        System.out.println("📝 CRIAÇÃO DE PERSONAGEM\n");

        String nome = null;

        while (nome == null || nome.trim().isEmpty()) {
            System.out.print("Digite o nome do seu herói: ");
            nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("⚠️ Nome não pode ser vazio!\n");
            } else if (nome.length() > 50) {
                System.out.println("⚠️ Nome muito longo! Máximo 50 caracteres.\n");
                nome = null;
            }
        }

        Heroi heroi = new Heroi(nome);

        System.out.println("\n✅ Herói criado com sucesso!");
        aguardarEnter();
        return heroi;
    }

    // ----------------------------------------
    private static void aguardarEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    // ----------------------------------------
    // MENU PRINCIPAL
    // ----------------------------------------
    private static boolean menuPrincipal(Heroi heroi) {
        limparTela();

        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║           MENU PRINCIPAL                  ║");
        System.out.println("╚═══════════════════════════════════════════╝\n");

        System.out.println(heroi + "\n");

        System.out.println("1. ⚔️  Iniciar Batalha");
        System.out.println("2. 📊 Ver Ranking");
        System.out.println("3. 📜 Histórico de Batalhas");
        System.out.println("4. 🚪 Sair do Jogo");
        System.out.print("\nEscolha uma opção: ");

        try {
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> iniciarBatalha(heroi);
                case "2" -> exibirRanking();
                case "3" -> exibirHistorico(heroi);
                case "4" -> {
                    return confirmarSaida();
                }
                default -> throw new AcaoInvalidaException("Opção inválida! Digite 1-4.", opcao);
            }

        } catch (AcaoInvalidaException e) {
            System.err.println("❌ " + e.getMessage());
            aguardarEnter();
        }

        return true;
    }

    // ----------------------------------------
    private static void iniciarBatalha(Heroi heroi) {
    limparTela();
    System.out.println("⚔️ INICIANDO BATALHA...\n");

    // Gerar vilão aleatório
    Vilao vilao = Vilao.gerarVilaoAleatorio();

    System.out.println("👹 Você encontrou um vilão!");
    System.out.println(vilao + "\n");
    aguardarEnter();

    Random random = new Random();

    // Loop da batalha
    while (heroi.isVivo() && vilao.isVivo()) {
        limparTela();
        System.out.println("===== BATALHA =====");
        System.out.println(heroi);
        System.out.println(vilao + "\n");

        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Especial");
        System.out.print("\nEscolha sua ação: ");

        String acao = scanner.nextLine();

        switch (acao) {
            case "1" -> {
                int dano = heroi.atacar();
                vilao.receberDano(dano);
                System.out.println("\n💥 Você atacou e causou " + dano + " de dano!");
            }
            case "2" -> {
                int cura = heroi.defender();
                System.out.println("\n🛡️ Você defendeu e recuperou " + cura + " de vida!");
            }
            case "3" -> {
                int dano = heroi.ataqueEspecial();
                vilao.receberDano(dano);
                System.out.println("\n🔥 ATAQUE ESPECIAL! Você causou " + dano + " de dano!");
            }
            default -> {
                System.out.println("❌ Ação inválida!");
                continue;
            }
        }

        aguardarEnter();

        if (!vilao.isVivo()) break;

        // Turno do inimigo
        limparTela();
        int danoVilao = vilao.atacar();
        heroi.receberDano(danoVilao);
        System.out.println("👹 O vilão atacou e te causou " + danoVilao + " de dano!");
        aguardarEnter();
    }

    limparTela();

    if (heroi.isVivo()) {
        System.out.println("🏆 VOCÊ VENCEU A BATALHA!");
        bancoDados.registrarVitoria(heroi);
    } else {
        System.out.println("💀 VOCÊ PERDEU...");
        bancoDados.registrarDerrota(heroi);
    }

    aguardarEnter();
}

    private static void exibirRanking() {
        System.out.println("\n📊 Ranking ainda será implementado...");
        aguardarEnter();
    }

    private static void exibirHistorico(Heroi heroi) {
        System.out.println("\n📜 Histórico ainda será implementado...");
        aguardarEnter();
    }

    private static boolean confirmarSaida() {
        System.out.print("\nDeseja sair do jogo? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s");
    }
}
