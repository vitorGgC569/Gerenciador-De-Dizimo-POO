package br.edu.ifgoiano.gerenciadorDeDizimo.servicos;

import br.edu.ifgoiano.gerenciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Fiel}. */
public class FielServico {

    /**
     * Cadastra um novo fiel inserindo em {@code usuario} e em {@code fiel}.
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

        String sqlUsuario = "INSERT INTO usuario (nome, email, senha_hash, is_admin, telefone) VALUES (?, ?, ?, ?, ?)";
        String sqlFiel    = "INSERT INTO fiel (id_usuario, data_batismo, id_paroquia) VALUES (?, ?, ?)";

        Connection con = null;
        try {
            con = ConexaoDB.getConexao();
            con.setAutoCommit(false);

            long idGerado;
            try (PreparedStatement stmtU = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stmtU.setString(1, fiel.getNome());
                stmtU.setString(2, fiel.getEmail());
                stmtU.setString(3, fiel.getSenhaHash());
                stmtU.setInt(4, fiel.isAdmin() ? 1 : 0);
                stmtU.setString(5, fiel.getTelefone());
                stmtU.executeUpdate();

                ResultSet rs = stmtU.getGeneratedKeys();
                if (!rs.next()) throw new RuntimeException("ID não gerado para o fiel.");
                idGerado = rs.getLong(1);
            }

            try (PreparedStatement stmtF = con.prepareStatement(sqlFiel)) {
                stmtF.setLong(1, idGerado);
                stmtF.setString(2, fiel.getDataBatismo() != null ? fiel.getDataBatismo().toString() : null);
                stmtF.setObject(3, fiel.getIdParoquia());
                stmtF.executeUpdate();
            }

            con.commit();
            fiel.setId(idGerado);
            return fiel;

        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw new RuntimeException("Não foi possível cadastrar o Fiel: " + e.getMessage());
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    /**
     * Busca um fiel pelo ID com JOIN entre {@code usuario} e {@code fiel}.
     * @param id identificador do fiel
     * @return fiel encontrado ou {@code null}
     */
    public Fiel buscarPorId(Long id) {
        String sql = """
            SELECT u.id, u.nome, u.email, u.senha_hash, u.is_admin, u.telefone,
                   f.data_batismo, f.id_paroquia
            FROM usuario u
            INNER JOIN fiel f ON u.id = f.id_usuario
            WHERE u.id = ?
        """;
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível encontrar o Fiel com esse ID: " + e.getMessage());
        }
        return null;
    }

    /** @return lista com todos os fiéis cadastrados */
    public List<Fiel> listarTodos() {
        String sql = """
            SELECT u.id, u.nome, u.email, u.senha_hash, u.is_admin, u.telefone,
                   f.data_batismo, f.id_paroquia
            FROM usuario u
            INNER JOIN fiel f ON u.id = f.id_usuario
        """;
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
     * Atualiza os dados do fiel nas tabelas {@code usuario} e {@code fiel}.
     * @param fiel fiel com dados atualizados (deve conter ID válido)
     * @return fiel atualizado
     */
    public Fiel atualizar(Fiel fiel) {
        String sqlUsuario = "UPDATE usuario SET nome = ?, email = ?, senha_hash = ?, is_admin = ?, telefone = ? WHERE id = ?";
        String sqlFiel    = "UPDATE fiel SET data_batismo = ?, id_paroquia = ? WHERE id_usuario = ?";

        Connection con = null;
        try {
            con = ConexaoDB.getConexao();
            con.setAutoCommit(false);

            try (PreparedStatement stmtU = con.prepareStatement(sqlUsuario)) {
                stmtU.setString(1, fiel.getNome());
                stmtU.setString(2, fiel.getEmail());
                stmtU.setString(3, fiel.getSenhaHash());
                stmtU.setInt(4, fiel.isAdmin() ? 1 : 0);
                stmtU.setString(5, fiel.getTelefone());
                stmtU.setLong(6, fiel.getId());
                stmtU.executeUpdate();
            }

            try (PreparedStatement stmtF = con.prepareStatement(sqlFiel)) {
                stmtF.setString(1, fiel.getDataBatismo() != null ? fiel.getDataBatismo().toString() : null);
                stmtF.setObject(2, fiel.getIdParoquia());
                stmtF.setLong(3, fiel.getId());
                stmtF.executeUpdate();
            }

            con.commit();
            return fiel;

        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw new RuntimeException("Erro ao atualizar fiel: " + e.getMessage());
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    /**
     * Remove um fiel pelo ID — o CASCADE cuida da tabela {@code fiel}.
     * @param id identificador do fiel
     * @return {@code true} se removido
     */
    public boolean remover(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
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
        fiel.setTelefone(rs.getString("telefone"));
        fiel.setIdParoquia(rs.getLong("id_paroquia"));
        return fiel;
    }
}
