package br.edu.ifgoiano.gerenciadorDeDizimo.servicos;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Paroquia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Testes unitários para {@link ParoquiaServico}. */
class ParoquiaServicoTest {

    private ParoquiaServico servico;
    private Paroquia paroquia;
    private Padre padre;

    /** Reinicia o serviço e cria uma paróquia base antes de cada teste. */
    @BeforeEach
    void setUp(){
        servico = new ParoquiaServico();
        padre = new Padre();
        padre.setNome("Pe. Carlos");
        padre.setEmail("carlos@paroquia.com");
        paroquia = new Paroquia("Nossa Senhora", "Rua A, 100", padre);
        paroquia.setNomeParoquia("Nossa Senhora");
        paroquia.setEndereco("Rua A, 100");
    }

    @Test
    @DisplayName("Deve cadastrar paróquia com sucesso")
    void deveCadastrarParoquia(){
        Paroquia cadastrada = servico.cadastrar(paroquia);
        assertNotNull(cadastrada);
        assertEquals("Nossa Senhora", cadastrada.getNomeParoquia());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com nome vazio")
    void deveLancarExcecaoNomeVazio(){
        paroquia.setNomeParoquia("");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(paroquia));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com endereço vazio")
    void deveLancarExcecaoEnderecoVazio(){
        paroquia.setEndereco("   ");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(paroquia));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar nome duplicado")
    void deveLancarExcecaoNomeDuplicado(){
        servico.cadastrar(paroquia);
        Paroquia duplicada = new Paroquia("Nossa Senhora", "Rua B, 200", padre);
        duplicada.setNomeParoquia("Nossa Senhora");
        duplicada.setEndereco("Rua B, 200");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(duplicada));
    }

    @Test
    @DisplayName("Deve encontrar paróquia pelo nome")
    void deveBuscarParoquiaPorNome(){
        servico.cadastrar(paroquia);
        Paroquia encontrada = servico.buscarPorNome("Nossa Senhora");
        assertNotNull(encontrada);
        assertEquals("Nossa Senhora", encontrada.getNomeParoquia());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar nome inexistente")
    void deveRetornarNullParaNomeInexistente(){
        assertNull(servico.buscarPorNome("Inexistente"));
    }

    @Test
    @DisplayName("Deve listar todas as paróquias cadastradas")
    void deveListarTodas(){
        servico.cadastrar(paroquia);
        Paroquia segunda = new Paroquia("São José", "Rua B, 200", padre);
        segunda.setNomeParoquia("São José");
        segunda.setEndereco("Rua B, 200");
        servico.cadastrar(segunda);
        List<Paroquia> lista = servico.listarTodas();
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Deve adicionar fiel e atualizar contagem")
    void deveAdicionarFiel(){
        servico.cadastrar(paroquia);
        Fiel fiel = new Fiel();
        fiel.setNome("Maria");
        fiel.setEmail("maria@email.com");
        servico.adicionarFiel("Nossa Senhora", fiel);
        assertEquals(1, servico.contarFieis("Nossa Senhora"));
    }

    @Test
    @DisplayName("Deve remover fiel e atualizar contagem")
    void deveRemoverFiel(){
        servico.cadastrar(paroquia);
        Fiel fiel = new Fiel();
        fiel.setNome("Maria");
        fiel.setEmail("maria@email.com");
        servico.adicionarFiel("Nossa Senhora", fiel);
        servico.removerFiel("Nossa Senhora", fiel);
        assertEquals(0, servico.contarFieis("Nossa Senhora"));
    }

    @Test
    @DisplayName("Deve remover paróquia existente")
    void deveRemoverParoquia(){
        servico.cadastrar(paroquia);
        boolean removida = servico.remover("Nossa Senhora");
        assertTrue(removida);
        assertNull(servico.buscarPorNome("Nossa Senhora"));
    }

    @Test
    @DisplayName("Deve retornar false ao remover paróquia inexistente")
    void deveRetornarFalseAoRemoverInexistente(){
        assertFalse(servico.remover("Inexistente"));
    }
}
