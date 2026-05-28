package br.edu.ifgoiano.genreciadorDeDizimo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gerencia a conexão única com o banco SQLite e cria as tabelas na primeira execução.
 */
public class ConexaoDB {

    // O arquivo paroquia.db será criado na raiz do projeto
    private static final String URL = "jdbc:sqlite:paroquia.db";

    private static Connection instancia;

    /**
     * Retorna ou abre a conexão singleton com o banco.
     *
     * @return conexão com o banco SQLite
     * @throws SQLException caso ocorra erro na conexão
     */
    public static Connection getConexao() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            instancia = DriverManager.getConnection(URL);
            instancia.setAutoCommit(true);

            // Ativa suporte a chaves estrangeiras no SQLite
            try (Statement stmt = instancia.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON");
            }

            criarTabelas(instancia);
        }

        return instancia;
    }

    /**
     * Fecha a conexão com o banco.
     */
    public static void fechar() {
        try {
            if (instancia != null && !instancia.isClosed()) {
                instancia.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    /**
     * Cria as tabelas do sistema caso ainda não existam.
     *
     * @param con conexão ativa com o banco
     * @throws SQLException caso ocorra erro na criação das tabelas
     */
    private static void criarTabelas(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE,
                    senha_hash TEXT NOT NULL,
                    is_admin INTEGER NOT NULL DEFAULT 0,
                    telefone TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS padre (
                    id_usuario INTEGER PRIMARY KEY,
                    data_ordenacao TEXT,

                    CONSTRAINT fk_padre_usuario
                        FOREIGN KEY (id_usuario)
                        REFERENCES usuario(id)
                        ON DELETE CASCADE
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS paroquia (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome_paroquia TEXT NOT NULL,
                    endereco TEXT NOT NULL,
                    id_padre INTEGER,

                    CONSTRAINT fk_paroquia_padre
                        FOREIGN KEY (id_padre)
                        REFERENCES padre(id_usuario)
                        ON DELETE SET NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS fiel (
                    id_usuario INTEGER PRIMARY KEY,
                    data_batismo TEXT,
                    id_paroquia INTEGER,

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
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    valor REAL NOT NULL,
                    tipo TEXT NOT NULL,
                    id_fiel INTEGER NOT NULL,
                    id_paroquia INTEGER NOT NULL,

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