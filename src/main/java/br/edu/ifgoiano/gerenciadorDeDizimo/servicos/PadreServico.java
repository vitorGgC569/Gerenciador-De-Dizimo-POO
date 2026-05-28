package br.edu.ifgoiano.gerenciadorDeDizimo.servicos;

import br.edu.ifgoiano.gerenciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Padre}. */
public class PadreServico {

    /**
     * Cadastra um novo padre.
     * @param padre padre a cadastrar
     * @return padre com ID preenchido
     */
    public Padre cadastrar(Padre padre){

        if (padre.getNome() == null || padre.getNome().isBlank()){
            throw new IllegalArgumentException(
                    "Nome não pode ser vazio."
            );
        }

        if (padre.getEmail() == null || padre.getEmail().isBlank()){
            throw new IllegalArgumentException(
                    "Email não pode ser vazio."
            );
        }

        String sql =
                "INSERT INTO usuario " +
                        "(tipo, nome, email, senha_hash, is_admin, data_ordenacao) " +
                        "VALUES ('PADRE', ?, ?, ?, ?, ?)";

        try (
                Connection con = ConexaoDB.getConexao();
                PreparedStatement stmt =
                        con.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            stmt.setString(1, padre.getNome());
            stmt.setString(2, padre.getEmail());
            stmt.setString(3, padre.getSenhaHash());

            stmt.setInt(
                    4,
                    padre.isAdmin() ? 1 : 0
            );

            stmt.setString(
                    5,
                    padre.getData_Ordenacao() != null
                            ? padre.getData_Ordenacao().toString()
                            : null
            );

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                padre.setId(rs.getLong(1));
            }

            return padre;

        } catch (SQLException e){

            throw new RuntimeException(
                    "Erro ao cadastrar padre: " +
                            e.getMessage()
            );
        }
    }

    /**
     * Busca padre por ID.
     */
    public Padre buscarPorId(Long id){

        String sql =
                "SELECT * FROM usuario " +
                        "WHERE id = ? AND tipo = 'PADRE'";

        try (
                Connection con = ConexaoDB.getConexao();
                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return mapear(rs);
            }

        } catch (SQLException e){

            throw new RuntimeException(
                    "Erro ao buscar padre: " +
                            e.getMessage()
            );
        }

        return null;
    }

    /**
     * Lista todos os padres.
     */
    public List<Padre> listarTodos(){

        String sql =
                "SELECT * FROM usuario " +
                        "WHERE tipo = 'PADRE'";

        List<Padre> lista = new ArrayList<>();

        try (
                Connection con = ConexaoDB.getConexao();
                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                lista.add(mapear(rs));
            }

        } catch (SQLException e){

            throw new RuntimeException(
                    "Erro ao listar padres: " +
                            e.getMessage()
            );
        }

        return lista;
    }

    /**
     * Atualiza os dados do padre.
     */
    public Padre atualizar(Padre padre){

        String sql =
                "UPDATE usuario SET " +
                        "nome = ?, " +
                        "email = ?, " +
                        "senha_hash = ?, " +
                        "is_admin = ?, " +
                        "data_ordenacao = ? " +
                        "WHERE id = ? AND tipo = 'PADRE'";

        try (
                Connection con = ConexaoDB.getConexao();
                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setString(1, padre.getNome());
            stmt.setString(2, padre.getEmail());
            stmt.setString(3, padre.getSenhaHash());

            stmt.setInt(
                    4,
                    padre.isAdmin() ? 1 : 0
            );

            stmt.setString(
                    5,
                    padre.getData_Ordenacao() != null
                            ? padre.getData_Ordenacao().toString()
                            : null
            );

            stmt.setLong(6, padre.getId());

            stmt.executeUpdate();

            return padre;

        } catch (SQLException e){

            throw new RuntimeException(
                    "Erro ao atualizar padre: " +
                            e.getMessage()
            );
        }
    }

    /**
     * Remove padre pelo ID.
     */
    public boolean remover(Long id){

        String sql =
                "DELETE FROM usuario " +
                        "WHERE id = ? AND tipo = 'PADRE'";

        try (
                Connection con = ConexaoDB.getConexao();
                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e){

            throw new RuntimeException(
                    "Erro ao remover padre: " +
                            e.getMessage()
            );
        }
    }

    /**
     * Converte ResultSet em objeto Padre.
     */
    private Padre mapear(ResultSet rs) throws SQLException {

        Padre padre = new Padre();

        padre.setId(rs.getLong("id"));
        padre.setNome(rs.getString("nome"));
        padre.setEmail(rs.getString("email"));
        padre.setSenhaHash(rs.getString("senha_hash"));

        padre.setAdmin(
                rs.getInt("is_admin") == 1
        );

        return padre;
    }
}