package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.entidades.Fiel;

import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Fiel}. */
public class FielServico {

    private List<Fiel> fieis =  new ArrayList<>();
    private Long proximoId = 1L;

    /**
     * Cadastra um novo fiel, validando nome, email e duplicatas.
     * @param fiel fiel a cadastrar
     * @return fiel com ID gerado
     */
    public Fiel cadastrar(Fiel fiel){
        if (fiel.getNome() == null || fiel.getNome().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio!!");
        }
        if (fiel.getEmail() == null || fiel.getEmail().isBlank()){
            throw new IllegalArgumentException("Email não pode ser vazio!!");
        }
        if (emailJaCadastrado(fiel.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        fiel.setId(proximoId++);
        fieis.add(fiel);
        return fiel;
    }

    /**
     * Busca um fiel pelo ID.
     * @param id identificador do fiel
     * @return fiel encontrado ou {@code null}
     */
    public Fiel buscarPorId(Long id){
        for (Fiel fiel : fieis){
            if (fiel.getId().equals(id)){
                return fiel;
            }
        }
        return null;
    }

    /** @return cópia da lista de todos os fiéis */
    public List<Fiel> listarTodos(){
        return new ArrayList<>(fieis);
    }

    /**
     * Atualiza os dados de um fiel existente.
     * @param fiel fiel com dados atualizados (deve conter ID válido)
     * @return fiel atualizado
     */
    public Fiel atualizar(Fiel fiel){
        Fiel existente = buscarPorId(fiel.getId());
        if (existente == null){throw new IllegalArgumentException("Fiel não encontrado.");}
        existente.setNome(fiel.getNome());
        existente.setEmail(fiel.getEmail());
        existente.setSenhaHash(fiel.getSenhaHash());
        existente.setDataBatismo(fiel.getDataBatismo());
        existente.setAdmin(fiel.isAdmin());
        return existente;
    }

    /**
     * Remove um fiel pelo ID.
     * @param id identificador do fiel
     * @return {@code true} se removido, {@code false} se não encontrado
     */
    public boolean remover(Long id){
        return fieis.removeIf(f -> f.getId().equals(id));
    }

    private boolean emailJaCadastrado(String email){
        for (Fiel fiel : fieis){
            if (fiel.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
}