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

            // Tabela base de usuários (estratégia table-per-class com type discriminator)
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS usuario (
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    tipo       TEXT    NOT NULL CHECK(tipo IN ('FIEL','PADRE')),
                    nome       TEXT    NOT NULL,
                    email      TEXT    NOT NULL UNIQUE,
                    senha_hash TEXT,
                    is_admin   INTEGER NOT NULL DEFAULT 0,

                    -- colunas exclusivas de Fiel
                    data_batismo TEXT,

                    -- colunas exclusivas de Padre
                    data_ordenacao TEXT,
                    salario        REAL
                )
            """);

            // Paróquia
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS paroquia (
                    id            INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome_paroquia TEXT NOT NULL UNIQUE,
                    endereco      TEXT NOT NULL,
                    padre_id      INTEGER REFERENCES usuario(id) ON DELETE SET NULL
                )
            """);

            // Tabela de associação Paróquia <-> Fiel (N:N)
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS paroquia_fiel (
                    paroquia_id INTEGER NOT NULL REFERENCES paroquia(id) ON DELETE CASCADE,
                    fiel_id     INTEGER NOT NULL REFERENCES usuario(id)  ON DELETE CASCADE,
                    PRIMARY KEY (paroquia_id, fiel_id)
                )
            """);

            // Dízimo (relacionado ao fiel)
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS dizimo (
                    id                       INTEGER PRIMARY KEY AUTOINCREMENT,
                    fiel_id                  INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
                    descricao                TEXT,
                    valor                    REAL    NOT NULL,
                    data_inicial             TEXT,
                    data_proxima_contribuicao TEXT,
                    contribuinte_recorrente  INTEGER NOT NULL DEFAULT 0
                )
            """);
        }
    }
}
