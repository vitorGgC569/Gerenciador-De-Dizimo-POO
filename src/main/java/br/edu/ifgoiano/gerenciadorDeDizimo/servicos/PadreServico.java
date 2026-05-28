package br.edu.ifgoiano.gerenciadorDeDizimo.servicos;

import br.edu.ifgoiano.gerenciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Padre}. */
public class PadreServico {

    /**
     * Cadastra um novo padre inserindo em {@code usuario} e em {@code padre}.
     * @param padre padre a cadastrar
     * @return padre com ID preenchido
     */
    public Padre cadastrar(Padre padre) {
        if (padre.getNome() == null || padre.getNome().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (padre.getEmail() == null || padre.getEmail().isBlank()){
            throw new IllegalArgumentException("Email não pode ser vazio.");
        }

        String sqlUsuario = "INSERT INTO usuario (nome, email, senha_hash, is_admin, telefone) VALUES (?, ?, ?, ?, ?)";
        String sqlPadre   = "INSERT INTO padre (id_usuario, data_ordenacao) VALUES (?, ?)";

        Connection con = null;
        try {
            con = ConexaoDB.getConexao();
            con.setAutoCommit(false);

            long idGerado;
            try (PreparedStatement stmtU = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stmtU.setString(1, padre.getNome());
                stmtU.setString(2, padre.getEmail());
                stmtU.setString(3, padre.getSenhaHash());
                stmtU.setInt(4, padre.isAdmin() ? 1 : 0);
                stmtU.setString(5, padre.getTelefone());
                stmtU.executeUpdate();

                ResultSet rs = stmtU.getGeneratedKeys();
                if (!rs.next()) throw new RuntimeException("ID não gerado para o padre.");
                idGerado = rs.getLong(1);
            }

            try (PreparedStatement stmtP = con.prepareStatement(sqlPadre)) {
                stmtP.setLong(1, idGerado);
                stmtP.setString(2, padre.getData_Ordenacao() != null ? padre.getData_Ordenacao().toString() : null);
                stmtP.executeUpdate();
            }

            con.commit();
            padre.setId(idGerado);
            return padre;

        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw new RuntimeException("Erro ao cadastrar padre: " + e.getMessage());
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    /**
     * Busca padre por ID com JOIN entre {@code usuario} e {@code padre}.
     * @param id identificador do padre
     * @return padre encontrado ou {@code null}
     */
    public Padre buscarPorId(Long id) {
        String sql = """
            SELECT u.id, u.nome, u.email, u.senha_hash, u.is_admin, p.data_ordenacao
            FROM usuario u
            INNER JOIN padre p ON u.id = p.id_usuario
            WHERE u.id = ?
        """;
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar padre: " + e.getMessage());
        }
        return null;
    }

    /** @return lista com todos os padres cadastrados */
    public List<Padre> listarTodos() {
        String sql = """
            SELECT u.id, u.nome, u.email, u.senha_hash, u.is_admin, p.data_ordenacao
            FROM usuario u
            INNER JOIN padre p ON u.id = p.id_usuario
        """;
        List<Padre> lista = new ArrayList<>();
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar padres: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os dados do padre nas tabelas {@code usuario} e {@code padre}.
     * @param padre padre com dados atualizados (deve conter ID válido)
     * @return padre atualizado
     */
    public Padre atualizar(Padre padre) {
        String sqlUsuario = "UPDATE usuario SET nome = ?, email = ?, senha_hash = ?, is_admin = ? WHERE id = ?";
        String sqlPadre   = "UPDATE padre SET data_ordenacao = ? WHERE id_usuario = ?";

        Connection con = null;
        try {
            con = ConexaoDB.getConexao();
            con.setAutoCommit(false);

            try (PreparedStatement stmtU = con.prepareStatement(sqlUsuario)) {
                stmtU.setString(1, padre.getNome());
                stmtU.setString(2, padre.getEmail());
                stmtU.setString(3, padre.getSenhaHash());
                stmtU.setInt(4, padre.isAdmin() ? 1 : 0);
                stmtU.setLong(5, padre.getId());
                stmtU.executeUpdate();
            }

            try (PreparedStatement stmtP = con.prepareStatement(sqlPadre)) {
                stmtP.setString(1, padre.getData_Ordenacao() != null ? padre.getData_Ordenacao().toString() : null);
                stmtP.setLong(2, padre.getId());
                stmtP.executeUpdate();
            }

            con.commit();
            return padre;

        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw new RuntimeException("Erro ao atualizar padre: " + e.getMessage());
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    /**
     * Remove padre pelo ID — o CASCADE cuida da tabela {@code padre}.
     * @param id identificador do padre
     * @return {@code true} se removido
     */
    public boolean remover(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover padre: " + e.getMessage());
        }
    }

    private Padre mapear(ResultSet rs) throws SQLException {
        Padre padre = new Padre();
        padre.setId(rs.getLong("id"));
        padre.setNome(rs.getString("nome"));
        padre.setEmail(rs.getString("email"));
        padre.setSenhaHash(rs.getString("senha_hash"));
        padre.setAdmin(rs.getInt("is_admin") == 1);
        return padre;
    }
}
