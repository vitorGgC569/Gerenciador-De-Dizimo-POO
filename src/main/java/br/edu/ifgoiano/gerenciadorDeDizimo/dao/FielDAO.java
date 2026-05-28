package br.edu.ifgoiano.gerenciadorDeDizimo.dao;

import br.edu.ifgoiano.gerenciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * DAO responsável pelas operações da tabela fiel.
 */
public class FielDAO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Insere um fiel no banco.
     *
     * Primeiro insere os dados em usuario.
     * Depois insere os dados específicos em fiel.
     *
     * @param fiel fiel que será inserido
     */
    public void inserir(Fiel fiel) {
        usuarioDAO.inserir(fiel);

        String sql = """
                INSERT INTO fiel (id_usuario, data_batismo, id_paroquia)
                VALUES (?, ?, ?)
                """;

        try {
            Connection con = ConexaoDB.getConexao();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, fiel.getId());

            if (fiel.getDataBatismo() != null) {
                String dataFormatada = new java.sql.Date(
                        fiel.getDataBatismo().getTime()
                ).toString();

                ps.setString(2, dataFormatada);
            } else {
                ps.setString(2, null);
            }

            if (fiel.getIdParoquia() != null) {
                ps.setLong(3, fiel.getIdParoquia());
            } else {
                ps.setObject(3, null);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir fiel: " + e.getMessage(), e);
        }
    }
}