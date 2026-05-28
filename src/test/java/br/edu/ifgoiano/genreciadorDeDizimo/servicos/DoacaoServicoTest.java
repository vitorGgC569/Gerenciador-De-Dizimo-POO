package br.edu.ifgoiano.genreciadorDeDizimo.servicos;

import br.edu.ifgoiano.genreciadorDeDizimo.db.ConexaoDB;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Doacao;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para {@link DoacaoServico}.
 * Usa o banco SQLite real — cada teste limpa as doações do fiel de teste antes de rodar.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DoacaoServicoTest {

    private static final long ID_FIEL_TESTE = 999L;

    private DoacaoServico servico;

    /**
     * Inicializa o banco e insere um fiel fictício para satisfazer a FK de doacao.
     * Roda uma única vez antes de todos os testes.
     */
    @BeforeAll
    static void inicializarBanco() throws SQLException {
        Connection con = ConexaoDB.getConexao();
        try (PreparedStatement stmt = con.prepareStatement(
                "INSERT OR IGNORE INTO usuario (id, tipo, nome, email, senha_hash, is_admin) VALUES (?, 'FIEL', 'Fiel Teste', 'teste@teste.com', 'hash', 0)")) {
            stmt.setLong(1, ID_FIEL_TESTE);
            stmt.executeUpdate();
        }
    }

    /** Recria o serviço e limpa as doações do fiel de teste antes de cada teste. */
    @BeforeEach
    void setUp() throws SQLException {
        servico = new DoacaoServico();
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM doacao WHERE fiel_id = ?")) {
            stmt.setLong(1, ID_FIEL_TESTE);
            stmt.executeUpdate();
        }
    }

    /** Remove o fiel fictício e fecha a conexão após todos os testes. */
    @AfterAll
    static void limparBanco() throws SQLException {
        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
            stmt.setLong(1, ID_FIEL_TESTE);
            stmt.executeUpdate();
        }
        ConexaoDB.fechar();
    }

    @Test
    @Order(1)
    @DisplayName("Deve registrar doação e gerar ID")
    void deveRegistrarDoacao() throws SQLException {
        Doacao doacao = criarDoacao(50.0, "Dízimo", "Nossa Senhora");

        Doacao registrada = servico.registrar(doacao);

        assertNotNull(registrada.getId());
        assertEquals(50.0, registrada.getValor());
    }

    @Test
    @Order(2)
    @DisplayName("Deve listar doações por fiel")
    void deveListarDoacoesPorFiel() throws SQLException {
        servico.registrar(criarDoacao(50.0, "Dízimo", "Nossa Senhora"));
        servico.registrar(criarDoacao(30.0, "Oferta", "Nossa Senhora"));

        List<Doacao> lista = servico.listarPorFiel(ID_FIEL_TESTE);

        assertEquals(2, lista.size());
    }

    @Test
    @Order(3)
    @DisplayName("Deve retornar lista vazia quando fiel não tem doações")
    void deveRetornarListaVazia() {
        List<Doacao> lista = servico.listarPorFiel(ID_FIEL_TESTE);

        assertTrue(lista.isEmpty());
    }

    @Test
    @Order(4)
    @DisplayName("Deve remover doação existente")
    void deveRemoverDoacao() throws SQLException {
        Doacao registrada = servico.registrar(criarDoacao(50.0, "Dízimo", "Nossa Senhora"));

        boolean removida = servico.remover(registrada.getId());

        assertTrue(removida);
        assertTrue(servico.listarPorFiel(ID_FIEL_TESTE).isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("Deve retornar false ao remover ID inexistente")
    void deveRetornarFalseAoRemoverInexistente() {
        assertFalse(servico.remover(99999L));
    }

    @Test
    @Order(6)
    @DisplayName("Deve calcular total das doações do fiel")
    void deveCalcularTotalPorFiel() throws SQLException {
        servico.registrar(criarDoacao(100.0, "Dízimo", "Nossa Senhora"));
        servico.registrar(criarDoacao(50.0, "Oferta", "Nossa Senhora"));

        double total = servico.calcularTotalPorFiel(ID_FIEL_TESTE);

        assertEquals(150.0, total);
    }

    @Test
    @Order(7)
    @DisplayName("Deve retornar 0.0 quando fiel não tem doações")
    void deveRetornarZeroSemDoacoes() {
        double total = servico.calcularTotalPorFiel(ID_FIEL_TESTE);

        assertEquals(0.0, total);
    }

    private Doacao criarDoacao(double valor, String tipo, String nomeParoquia) {
        Doacao d = new Doacao();
        d.setIdFiel(ID_FIEL_TESTE);
        d.setValor(valor);
        d.setTipo(tipo);
        d.setNomeParoquia(nomeParoquia);
        return d;
    }
}
