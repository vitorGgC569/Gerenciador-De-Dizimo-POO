package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.entidades.Padre;

import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Padre}. */
public class PadreServico {

    private List<Padre> padres = new ArrayList<>();
    private Long proximoId = 1L;

    /**
     * Cadastra um novo padre, validando nome, email e duplicatas.
     * @param padre padre a cadastrar
     * @return padre com ID gerado
     */
    public Padre cadastrar(Padre padre){
        if (padre.getNome() == null || padre.getNome().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio!!");
        }
        if (padre.getEmail() == null || padre.getEmail().isBlank()){
            throw new IllegalArgumentException("Email não pode ser vazio!!");
        }
        if (emailJaCadastrado(padre.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        padre.setId(proximoId++);
        padres.add(padre);
        return padre;
    }

    /**
     * Busca um padre pelo ID.
     * @param id identificador do padre
     * @return padre encontrado ou {@code null}
     */
    public Padre buscarPorId(Long id){
        for (Padre padre : padres){
            if (padre.getId().equals(id))
                return padre;
        }
        return null;
    }

    /** @return cópia da lista de todos os padres */
    public List<Padre> listarTodos(){
        return new ArrayList<>(padres);
    }

    /**
     * Atualiza os dados de um padre existente.
     * @param padre padre com dados atualizados (deve conter ID válido)
     * @return padre atualizado
     */
    public Padre atualizar(Padre padre){
        Padre existente = buscarPorId(padre.getId());
        if (existente == null){throw new IllegalArgumentException("Padre não encontrado.");}
        existente.setNome(padre.getNome());
        existente.setEmail(padre.getEmail());
        existente.setSenhaHash(padre.getSenhaHash());
        existente.setData_Ordenacao(padre.getData_Ordenacao());
        return existente;
    }

    /**
     * Remove um padre pelo ID.
     * @param id identificador do padre
     * @return {@code true} se removido, {@code false} se não encontrado
     */
    public boolean remover(Long id){
        return padres.removeIf(p -> p.getId().equals(id));
    }


    private boolean emailJaCadastrado(String email){
        for (Padre padre : padres){
            if (padre.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

}