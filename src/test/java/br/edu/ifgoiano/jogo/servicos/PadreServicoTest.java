package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.entidades.Padre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Testes unitários para {@link PadreServico}. */
class PadreServicoTest {

    private PadreServico servico;
    private Padre padre;

    /** Reinicia o serviço e cria um padre base antes de cada teste. */
    @BeforeEach
    void setUp(){
        servico = new PadreServico();

        padre = new Padre();
        padre.setNome("Pe. Carlos");
        padre.setEmail("carlos@paroquia.com");
        padre.setSenhaHash("hash456");
    }

    @Test
    @DisplayName("Deve cadastrar padre e gerar ID")
    void deveCadastrarPadre(){
        Padre cadastrado = servico.cadastrar(padre);
        assertNotNull(cadastrado.getId());
        assertEquals("Pe. Carlos", cadastrado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com nome vazio")
    void deveLancarExcecaoNomeVazio(){
        padre.setNome("   ");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(padre));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com email duplicado")
    void deveLancarExcecaoEmailDuplicado(){
        servico.cadastrar(padre);
        Padre duplicado = new Padre();
        duplicado.setNome("Pe. Roberto");
        duplicado.setEmail("carlos@paroquia.com");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(duplicado));
    }

    @Test
    @DisplayName("Deve encontrar padre por ID")
    void deveBuscarPadrePorId(){
        Padre cadastrado = servico.cadastrar(padre);
        Padre encontrado = servico.buscarPorId(cadastrado.getId());
        assertNotNull(encontrado);
        assertEquals(cadastrado.getId(), encontrado.getId());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullParaIdInexistente(){
        assertNull(servico.buscarPorId(99L));
    }

    @Test
    @DisplayName("Deve listar todos os padres cadastrados")
    void deveListarTodos(){
        servico.cadastrar(padre);
        Padre segundo = new Padre();
        segundo.setNome("Pe. Roberto");
        segundo.setEmail("roberto@paroquia.com");
        servico.cadastrar(segundo);
        List<Padre> lista = servico.listarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Deve atualizar dados do padre")
    void deveAtualizarPadre(){
        Padre cadastrado = servico.cadastrar(padre);
        cadastrado.setNome("Pe. Carlos Atualizado");
        Padre atualizado = servico.atualizar(cadastrado);
        assertEquals("Pe. Carlos Atualizado", atualizado.getNome());
    }

    @Test
    @DisplayName("Deve remover padre existente")
    void deveRemoverPadre(){
        Padre cadastrado = servico.cadastrar(padre);
        boolean removido = servico.remover(cadastrado.getId());
        assertTrue(removido);
        assertNull(servico.buscarPorId(cadastrado.getId()));
    }

    @Test
    @DisplayName("Deve retornar false ao remover ID inexistente")
    void deveRetornarFalseAoRemoverInexistente(){
        assertFalse(servico.remover(99L));
    }
}
