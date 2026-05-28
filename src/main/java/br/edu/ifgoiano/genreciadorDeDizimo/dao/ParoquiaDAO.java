package br.edu.ifgoiano.genreciadorDeDizimo.dao;

import br.edu.ifgoiano.genreciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Paroquia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DAO responsável pelas operações da tabela paroquia.
 */
public class ParoquiaDAO {

    /**
     * Insere uma paróquia no banco e guarda o ID gerado no objeto.
     *
     * @param paroquia paróquia que será inserida
     */
    public void inserir(Paroquia paroquia) {
        String sql = """
                INSERT INTO paroquia (nome_paroquia, endereco, id_padre)
                VALUES (?, ?, ?)
                """;

        try {
            Connection con = ConexaoDB.getConexao();

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, paroquia.getNomeParoquia());
            ps.setString(2, paroquia.getEndereco());

            if (paroquia.getPadre() != null && paroquia.getPadre().getId() != null) {
                ps.setLong(3, paroquia.getPadre().getId());
            } else {
                ps.setObject(3, null);
            }

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                paroquia.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir paróquia: " + e.getMessage(), e);
        }
    }
}