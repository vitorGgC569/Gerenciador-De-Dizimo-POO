package br.edu.ifgoiano.genreciadorDeDizimo.dao;

import br.edu.ifgoiano.genreciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Doacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DAO responsável pelas operações da tabela doacao.
 */
public class DoacaoDAO {

    /**
     * Insere uma doação no banco e guarda o ID gerado no objeto.
     *
     * @param doacao doação que será inserida
     */
    public void inserir(Doacao doacao) {
        String sql = """
                INSERT INTO doacao (valor, tipo, id_fiel, id_paroquia)
                VALUES (?, ?, ?, ?)
                """;

        try {
            Connection con = ConexaoDB.getConexao();

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setDouble(1, doacao.getValor());
            ps.setString(2, doacao.getTipo());
            ps.setLong(3, doacao.getIdFiel());
            ps.setLong(4, doacao.getIdParoquia());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                doacao.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir doação: " + e.getMessage(), e);
        }
    }
}