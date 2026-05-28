package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.db.ConexaoDB;
import br.edu.ifgoiano.jogo.entidades.Doacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoacaoServico {

    /**
     * Registra uma nova doação no banco e atribui o ID gerado.
     * @param doacao doação a registrar
     * @return doação com ID preenchido
     */
    public Doacao registrar(Doacao doacao) throws SQLException {
        String sql = "INSERT INTO doacao (fiel_id, valor, tipo, nome_paroquia) VALUES (?,?,?,?)";
        try(Connection con = ConexaoDB.getConexao(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setLong(1, doacao.getIdFiel());
            stmt.setDouble(2, doacao.getValor());
            stmt.setString(3, doacao.getTipo());
            stmt.setString(4, doacao.getNomeParoquia());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) doacao.setId(rs.getLong(1));

            return doacao;
        } catch (SQLException e){
            throw new RuntimeException("Erro ao registrar doação: " + e.getMessage());
        }
    }

    /**
     * Lista todas as doações de um fiel.
     * @param idFiel id do fiel
     * @return lista de doações
     */
    public List<Doacao> listarPorFiel(long idFiel) {
        String sql = "SELECT * FROM doacao WHERE fiel_id = ?";
        List<Doacao> lista = new ArrayList<>();
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, idFiel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doacao d = new Doacao();
                d.setId(rs.getLong("id"));
                d.setIdFiel(rs.getLong("fiel_id"));
                d.setValor(rs.getDouble("valor"));
                d.setTipo(rs.getString("tipo"));
                d.setNomeParoquia(rs.getString("nome_paroquia"));
                lista.add(d);
            }
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
    public boolean remover(long id) {
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
    public double calcularTotalPorFiel(long idFiel) {
        String sql = "SELECT SUM(valor) FROM doacao WHERE fiel_id = ?";
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
}
