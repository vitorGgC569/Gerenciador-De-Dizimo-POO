package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.entidades.Fiel;
import br.edu.ifgoiano.jogo.entidades.Paroquia;

import java.util.ArrayList;
import java.util.List;

public class ParoquiaServico {
    private List<Paroquia> paroquias = new ArrayList<>();

    public Paroquia cadastrar(Paroquia paroquia){
    if (paroquia.getNomeParoquia() == null || paroquia.getNomeParoquia().isBlank()){
        throw new IllegalArgumentException("Nome da Paroquia não pode ser vazio!!");
    }
    if (paroquia.getEndereco() == null || paroquia.getEndereco().isBlank()){
        throw new IllegalArgumentException("Endereço da Paroquia não pode ser vazio!!");
    }
    if (nomeJaCadastrado(paroquia.getNomeParoquia())){
        throw new IllegalArgumentException("Já existe uma paróquia com esse nome.");
    }
        paroquias.add(paroquia);
        return paroquia;
    }

    public Paroquia buscarPorNome(String nome){
        for (Paroquia paroquia : paroquias){
            if (paroquia.getNomeParoquia().equalsIgnoreCase(nome));
            return paroquia;
        }
        return null;
    }

    public List<Paroquia> listarTodas(){
        return new ArrayList<>(paroquias);
    }

    public Paroquia atualizar (String nomeAntigo, Paroquia novosDados){
        Paroquia existente = buscarPorNome(nomeAntigo);
        if (existente == null){throw new IllegalArgumentException("Paróquia não encontrada.");}
        existente.setNomeParoquia(novosDados.getNomeParoquia());
        existente.setEndereco(novosDados.getEndereco());
        return existente;
    }

    public boolean remover (String nome){
        return paroquias.removeIf(p -> p.getNomeParoquia().equalsIgnoreCase(nome));
    }

    public void adicionarFiel(String nomeParoquia, Fiel fiel){
        Paroquia paroquia = buscarPorNome(nomeParoquia);
        if (paroquia == null){throw new IllegalArgumentException("Paróquia não encontrada.");}
        paroquia.getFies().add(fiel);
        paroquia.setQtdeFieis(paroquia.getFies().size());
    }

    public void removerFiel(String nomeParoquia, Fiel fiel){
        Paroquia paroquia = buscarPorNome(nomeParoquia);
        if (paroquia == null){throw new IllegalArgumentException("Paróquia não encontrada.");}
        paroquia.getFies().remove(fiel);
        paroquia.setQtdeFieis(paroquia.getFies().size());
    }


    public int contarFieis(String nomeParoquia){
        Paroquia paroquia = buscarPorNome(nomeParoquia);
        if (paroquia == null){
            throw new IllegalArgumentException("Paroquia não encontrada.");
        }
        return paroquia.getFies().size();
    }

    private boolean nomeJaCadastrado (String nome){
        for (Paroquia paroquia : paroquias){
            if (paroquia.getNomeParoquia().equalsIgnoreCase(nome)) return true;
        }
        return false;
    }
}
