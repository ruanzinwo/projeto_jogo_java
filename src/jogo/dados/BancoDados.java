package jogo.dados;

import java.sql.*;
import jogo.excecoes.*;
import jogo.modelo.Heroi;

import java.util.*;

@SuppressWarnings("unused")
public class BancoDados {

    private static final String URL = "jdbc:sqlite:jogo_batalha.db";
    private Connection conexao;

    public BancoDados() {
        conectar();
        criarTabelas();
    }

    /**
     * Conecta ao banco de dados
     */
    private void conectar() {
        try {
            conexao = DriverManager.getConnection(URL);
            System.out.println("✔ Conexão com banco estabelecida!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    /**
     * Cria as tabelas caso não existam
     */
    private void criarTabelas() {
        String sqlJogador = """
            CREATE TABLE IF NOT EXISTS jogador (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                nivel INTEGER DEFAULT 1,
                experiencia INTEGER DEFAULT 0
            );
        """;

        String sqlBatalha = """
            CREATE TABLE IF NOT EXISTS batalha (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_jogador INTEGER NOT NULL,
                id_inimigo INTEGER NOT NULL,
                vencedor TEXT,
                data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlJogador);
            stmt.execute(sqlBatalha);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    /**
     * Registra jogador no banco (caso queira usar)
     */
    public int registrarJogador(String nome) {
        String sql = "INSERT INTO jogador (nome) VALUES (?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Erro ao registrar jogador: " + e.getMessage());
        }

        return -1;
    }

    /**
     * Cria uma batalha no banco antes dela começar
     */
    public int criarBatalha(int idJogador, int idInimigo) {
        String sql = "INSERT INTO batalha (id_jogador, id_inimigo) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idJogador);
            stmt.setInt(2, idInimigo);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("❌ Erro ao criar batalha: " + e.getMessage());
        }

        return -1;
    }

    /**
     * REGISTRA A VITÓRIA NO BANCO
     */
    public void registrarVitoria(int idBatalha, String vencedor) {
        String sql = "UPDATE batalha SET vencedor = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, vencedor);
            stmt.setInt(2, idBatalha);

            stmt.executeUpdate();
            System.out.println("🏆 Vitória registrada no banco de dados!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao registrar vitória: " + e.getMessage());
        }
    }

    /**
     * Encerra conexão
     */
    public void fechar() {
        try {
            if (conexao != null) {
                conexao.close();
                System.out.println("✔ Conexão com banco fechada!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar banco: " + e.getMessage());
        }
    }

    public void registrarVitoria(Heroi heroi) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarVitoria'");
    }

    public void registrarDerrota(Heroi heroi) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarDerrota'");
    }
}
