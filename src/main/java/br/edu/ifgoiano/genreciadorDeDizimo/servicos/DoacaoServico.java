package br.edu.ifgoiano.genreciadorDeDizimo.servicos;

import br.edu.ifgoiano.genreciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Doacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Doacao}. */
public class DoacaoServico {

    /**
     * Registra uma nova doação no banco e atribui o ID gerado.
     * @param doacao doação a registrar
     * @return doação com ID preenchido
     */
    public Doacao registrar(Doacao doacao) {
        String sql = "INSERT INTO doacao (id_fiel, valor, tipo, id_paroquia) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, doacao.getIdFiel());
            stmt.setDouble(2, doacao.getValor());
            stmt.setString(3, doacao.getTipo());
            stmt.setLong(4, doacao.getIdParoquia());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) doacao.setId(rs.getLong(1));

            return doacao;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar doação: " + e.getMessage());
        }
    }

    /**
     * Lista todas as doações de um fiel.
     * @param idFiel id do fiel
     * @return lista de doações
     */
    public List<Doacao> listarPorFiel(Long idFiel) {
        String sql = "SELECT * FROM doacao WHERE id_fiel = ?";
        List<Doacao> lista = new ArrayList<>();
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, idFiel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar doações: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Remove uma doação pelo ID.
     * @param id id da doação
     * @return {@code true} se removida
     */
    public boolean remover(Long id) {
        String sql = "DELETE FROM doacao WHERE id = ?";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover doação: " + e.getMessage());
        }
    }

    /**
     * Soma o total doado por um fiel.
     * @param idFiel id do fiel
     * @return soma dos valores ou 0.0 se nenhuma doação
     */
    public double calcularTotalPorFiel(Long idFiel) {
        String sql = "SELECT SUM(valor) FROM doacao WHERE id_fiel = ?";
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, idFiel);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular total: " + e.getMessage());
        }
        return 0.0;
    }

    private Doacao mapear(ResultSet rs) throws SQLException {
        Doacao d = new Doacao();
        d.setId(rs.getLong("id"));
        d.setIdFiel(rs.getLong("id_fiel"));
        d.setValor(rs.getDouble("valor"));
        d.setTipo(rs.getString("tipo"));
        d.setIdParoquia(rs.getLong("id_paroquia"));
        return d;
    }
}
