package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.db.ConexaoDB;
import br.edu.ifgoiano.jogo.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável pelas operações de banco da entidade Usuario.
 */
public class UsuarioDAO {

    /**
     * Insere um usuário no banco e retorna o ID gerado.
     *
     * @param usuario usuário que será inserido
     * @return ID gerado pelo banco
     */
    public Long inserir(Usuario usuario) {
        String sql = """
                INSERT INTO usuario (nome, email, senha_hash, is_admin)
                VALUES (?, ?, ?, ?)
                """;

        try {
            Connection con = ConexaoDB.getConexao();

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenhaHash());
            ps.setInt(4, usuario.isAdmin() ? 1 : 0);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                Long idGerado = rs.getLong(1);
                usuario.setId(idGerado);
                return idGerado;
            }

            throw new RuntimeException("Erro: nenhum ID foi gerado para o usuário.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }
}