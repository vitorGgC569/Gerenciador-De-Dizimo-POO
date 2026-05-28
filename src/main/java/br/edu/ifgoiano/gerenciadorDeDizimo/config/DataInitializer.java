package br.edu.ifgoiano.gerenciadorDeDizimo.config;

import br.edu.ifgoiano.gerenciadorDeDizimo.db.ConexaoDB;

import java.sql.SQLException;

/**
 * Responsável por inicializar o banco de dados na subida do projeto
 * e registrar o encerramento da conexão ao fechar a aplicação.
 */
public class DataInitializer {

    /**
     * Abre a conexão com o banco, criando as tabelas se ainda não existirem.
     * Registra um shutdown hook para fechar a conexão ao encerrar a JVM.
     */
    public static void inicializar() {
        try {
            ConexaoDB.getConexao();
            Runtime.getRuntime().addShutdownHook(new Thread(ConexaoDB::fechar));
            System.out.println("Banco de dados inicializado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
            System.exit(1);
        }
    }
}
