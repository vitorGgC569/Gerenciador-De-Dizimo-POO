package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.entidades.Fiel;

import java.util.ArrayList;
import java.util.List;

public class FielServico {
    private List<Fiel> fieis =  new ArrayList<>();
    private Long proximoId = 1L;

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

    public Fiel buscarPorId(Long id){
        for (Fiel fiel : fieis){
            if (fiel.getId().equals(id)){
                return fiel;
            }
        }
        return null;
    }

    public List<Fiel> listarTodos(){
        return new ArrayList<>(fieis);
    }

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