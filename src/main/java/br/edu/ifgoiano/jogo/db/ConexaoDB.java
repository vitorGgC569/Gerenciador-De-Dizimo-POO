package br.edu.ifgoiano.jogo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gerencia a conexão única com o banco SQLite e cria as tabelas na primeira execução.
 */
public class ConexaoDB {

    // O arquivo dizimo.db será criado na raiz do projeto
    private static final String URL = "jdbc:sqlite:dizimo.db";

    private static Connection instancia;

    /** Retorna (ou abre) a conexão singleton com o banco. */
    public static Connection getConexao() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            instancia = DriverManager.getConnection(URL);
            instancia.setAutoCommit(true);
            criarTabelas(instancia);
        }
        return instancia;
    }

    /** Fecha a conexão (chame ao encerrar o programa). */
    public static void fechar() {
        try {
            if (instancia != null && !instancia.isClosed()) {
                instancia.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    // ── DDL ──────────────────────────────────────────────────────────────────

    private static void criarTabelas(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {

            // Ativa suporte a chaves estrangeiras no SQLite
            stmt.execute("PRAGMA foreign_keys = ON");

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS usuario (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        email VARCHAR(150) NOT NULL UNIQUE,
                        senha_hash VARCHAR(255) NOT NULL,
                        is_admin BOOLEAN NOT NULL DEFAULT FALSE
                    )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS paroquia (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    nome_paroquia VARCHAR(150) NOT NULL,
                    endereco VARCHAR(255) NOT NULL,
                    id_padre BIGINT,
                
                    CONSTRAINT fk_paroquia_padre
                        FOREIGN KEY (id_padre)
                        REFERENCES padre(id_usuario)
                        ON DELETE SET NULL
                )
            """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS padre (
                id_usuario BIGINT PRIMARY KEY,
                data_ordenacao DATE,
            
                CONSTRAINT fk_padre_usuario
                    FOREIGN KEY (id_usuario)
                    REFERENCES usuario(id)
                    ON DELETE CASCADE
            )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS fiel (
                    id_usuario BIGINT PRIMARY KEY,
                    data_batismo DATE,
                    id_paroquia BIGINT,
                
                    CONSTRAINT fk_fiel_usuario
                        FOREIGN KEY (id_usuario)
                        REFERENCES usuario(id)
                        ON DELETE CASCADE,
                
                    CONSTRAINT fk_fiel_paroquia
                        FOREIGN KEY (id_paroquia)
                        REFERENCES paroquia(id)
                        ON DELETE SET NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS doacao (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    valor DOUBLE NOT NULL,
                    tipo VARCHAR(100) NOT NULL,
                    id_fiel BIGINT NOT NULL,
                    id_paroquia BIGINT NOT NULL,
                
                    CONSTRAINT fk_doacao_fiel
                        FOREIGN KEY (id_fiel)
                        REFERENCES fiel(id_usuario)
                        ON DELETE CASCADE,
                
                    CONSTRAINT fk_doacao_paroquia
                        FOREIGN KEY (id_paroquia)
                        REFERENCES paroquia(id)
                        ON DELETE CASCADE
                )
            """);
        }
    }
}
