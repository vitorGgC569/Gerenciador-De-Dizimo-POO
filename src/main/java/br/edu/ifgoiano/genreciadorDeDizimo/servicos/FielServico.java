package br.edu.ifgoiano.genreciadorDeDizimo.servicos;

import br.edu.ifgoiano.genreciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Fiel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Fiel}. */
public class FielServico {

    /**
     * Cadastra um novo fiel na tabela usuario e atribui o ID gerado.
     * @param fiel fiel a cadastrar
     * @return fiel com ID preenchido
     */
    public Fiel cadastrar(Fiel fiel) {
        if (fiel.getNome() == null || fiel.getNome().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio!!");
        }
        if (fiel.getEmail() == null || fiel.getEmail().isBlank()){
            throw new IllegalArgumentException("Email não pode ser vazio!!");
        }
        String sql = "INSERT INTO usuario (tipo, nome, email, senha_hash, is_admin, data_batismo) VALUES ('FIEL', ?, ?, ?, ?, ?)";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fiel.getNome());
            stmt.setString(2, fiel.getEmail());
            stmt.setString(3, fiel.getSenhaHash());
            stmt.setInt(4, fiel.isAdmin() ? 1 : 0);
            stmt.setString(5, fiel.getDataBatismo() != null ? fiel.getDataBatismo().toString() : null);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) fiel.setId(rs.getLong(1));

            return fiel;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível cadastrar o Fiel: " + e.getMessage());
        }
    }

    /**
     * Busca um fiel pelo ID.
     * @param id identificador do fiel
     * @return fiel encontrado ou {@code null}
     */
    public Fiel buscarPorId(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ? AND tipo = 'FIEL'";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível encontrar o Fiel com esse ID: " + e.getMessage());
        }
        return null;
    }

    /** @return lista com todos os fiéis cadastrados */
    public List<Fiel> listarTodos() {
        String sql = "SELECT * FROM usuario WHERE tipo = 'FIEL'";
        List<Fiel> lista = new ArrayList<>();
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar fiéis: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os dados de um fiel existente.
     * @param fiel fiel com dados atualizados (deve conter ID válido)
     * @return fiel atualizado
     */
    public Fiel atualizar(Fiel fiel) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha_hash = ?, is_admin = ?, data_batismo = ? WHERE id = ? AND tipo = 'FIEL'";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, fiel.getNome());
            stmt.setString(2, fiel.getEmail());
            stmt.setString(3, fiel.getSenhaHash());
            stmt.setInt(4, fiel.isAdmin() ? 1 : 0);
            stmt.setString(5, fiel.getDataBatismo() != null ? fiel.getDataBatismo().toString() : null);
            stmt.setLong(6, fiel.getId());
            stmt.executeUpdate();

            return fiel;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fiel: " + e.getMessage());
        }
    }

    /**
     * Remove um fiel pelo ID.
     * @param id identificador do fiel
     * @return {@code true} se removido, {@code false} se não encontrado
     */
    public boolean remover(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ? AND tipo = 'FIEL'";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover fiel: " + e.getMessage());
        }
    }

    private Fiel mapear(ResultSet rs) throws SQLException {
        Fiel fiel = new Fiel();
        fiel.setId(rs.getLong("id"));
        fiel.setNome(rs.getString("nome"));
        fiel.setEmail(rs.getString("email"));
        fiel.setSenhaHash(rs.getString("senha_hash"));
        fiel.setAdmin(rs.getInt("is_admin") == 1);
        return fiel;
    }
}
