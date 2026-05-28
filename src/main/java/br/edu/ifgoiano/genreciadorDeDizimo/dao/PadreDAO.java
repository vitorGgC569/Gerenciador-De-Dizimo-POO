package br.edu.ifgoiano.genreciadorDeDizimo.dao;

import br.edu.ifgoiano.genreciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Padre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável pelas operações de banco da entidade Padre.
 */
public class PadreDAO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Insere um padre no banco.
     *
     * Primeiro salva os dados gerais na tabela usuario.
     * Depois salva os dados específicos na tabela padre.
     *
     * @param padre padre que será inserido
     */
    public void inserir(Padre padre) {
        usuarioDAO.inserir(padre);

        String sql = """
                INSERT INTO padre (id_usuario, data_ordenacao)
                VALUES (?, ?)
                """;

        try {
            Connection con = ConexaoDB.getConexao();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, padre.getId());

            if (padre.getData_Ordenacao() != null) {
                ps.setDate(2, new java.sql.Date(padre.getData_Ordenacao().getTime()));
            } else {
                ps.setDate(2, null);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir padre: " + e.getMessage(), e);
        }
    }
}