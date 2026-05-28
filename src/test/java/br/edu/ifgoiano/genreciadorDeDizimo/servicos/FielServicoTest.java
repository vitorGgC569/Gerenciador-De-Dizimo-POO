package br.edu.ifgoiano.genreciadorDeDizimo.servicos;

import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Fiel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Testes unitários para {@link FielServico}. */
class FielServicoTest {

    private FielServico servico;
    private Fiel fiel;

    /** Reinicia o serviço e cria um fiel base antes de cada teste. */
    @BeforeEach
    void setUp(){
        servico = new FielServico();
        fiel = new Fiel();
        fiel.setNome("João Silva");
        fiel.setEmail("joao@email.com");
        fiel.setSenhaHash("hash123");
    }

    @Test
    @DisplayName("Deve cadastrar fiel e gerar ID")
    void deveCadastrarFiel(){
        Fiel cadastrado = servico.cadastrar(fiel);
        assertNotNull(cadastrado.getId());
        assertEquals("João Silva", cadastrado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com nome vazio")
    void deveLancarExcecaoNomeVazio(){
        fiel.setNome("");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(fiel));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com email duplicado")
    void deveLancarExcecaoEmailDuplicado(){
        servico.cadastrar(fiel);
        Fiel duplicado = new Fiel();
        duplicado.setNome("Maria");
        duplicado.setEmail("joao@email.com");
        assertThrows(IllegalArgumentException.class, () -> servico.cadastrar(duplicado));
    }

    @Test
    @DisplayName("Deve encontrar fiel por ID")
    void deveBuscarFielPorId(){
        Fiel cadastrado = servico.cadastrar(fiel);
        Fiel encontrado = servico.buscarPorId(cadastrado.getId());
        assertNotNull(encontrado);
        assertEquals(cadastrado.getId(), encontrado.getId());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullParaIdInexistente(){
        assertNull(servico.buscarPorId(99L));
    }

    @Test
    @DisplayName("Deve listar todos os fiéis cadastrados")
    void deveListarTodos(){
        servico.cadastrar(fiel);
        Fiel segundo = new Fiel();
        segundo.setNome("Maria");
        segundo.setEmail("maria@email.com");
        servico.cadastrar(segundo);
        List<Fiel> lista = servico.listarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Deve atualizar dados do fiel")
    void deveAtualizarFiel(){
        Fiel cadastrado = servico.cadastrar(fiel);
        cadastrado.setNome("João Atualizado");
        Fiel atualizado = servico.atualizar(cadastrado);
        assertEquals("João Atualizado", atualizado.getNome());
    }

    @Test
    @DisplayName("Deve remover fiel existente")
    void deveRemoverFiel(){
        Fiel cadastrado = servico.cadastrar(fiel);
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
