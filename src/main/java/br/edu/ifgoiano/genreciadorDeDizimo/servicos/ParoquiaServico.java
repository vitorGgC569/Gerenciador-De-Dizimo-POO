package br.edu.ifgoiano.genreciadorDeDizimo.servicos;

import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.genreciadorDeDizimo.entidades.Paroquia;

import java.util.ArrayList;
import java.util.List;

/** Serviço responsável pelas operações de CRUD de {@link Paroquia} e gestão de fiéis vinculados. */
public class ParoquiaServico {

    private List<Paroquia> paroquias = new ArrayList<>();

    /**
     * Cadastra uma nova paróquia, validando nome, endereço e duplicatas.
     * @param paroquia paróquia a cadastrar
     * @return paróquia cadastrada
     */
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

    /**
     * Busca uma paróquia pelo nome (case-insensitive).
     * @param nome nome da paróquia
     * @return paróquia encontrada ou {@code null}
     */
    public Paroquia buscarPorNome(String nome){
        for (Paroquia paroquia : paroquias){
            if (paroquia.getNomeParoquia().equalsIgnoreCase(nome));
            return paroquia;
        }
        return null;
    }

    /** @return cópia da lista de todas as paróquias */
    public List<Paroquia> listarTodas(){
        return new ArrayList<>(paroquias);
    }

    /**
     * Atualiza nome e endereço de uma paróquia existente.
     * @param nomeAntigo nome atual da paróquia
     * @param novosDados objeto com os novos dados
     * @return paróquia atualizada
     */
    public Paroquia atualizar (String nomeAntigo, Paroquia novosDados){
        Paroquia existente = buscarPorNome(nomeAntigo);
        if (existente == null){throw new IllegalArgumentException("Paróquia não encontrada.");}
        existente.setNomeParoquia(novosDados.getNomeParoquia());
        existente.setEndereco(novosDados.getEndereco());
        return existente;
    }

    /**
     * Remove uma paróquia pelo nome.
     * @param nome nome da paróquia
     * @return {@code true} se removida, {@code false} se não encontrada
     */
    public boolean remover (String nome){
        return paroquias.removeIf(p -> p.getNomeParoquia().equalsIgnoreCase(nome));
    }

    /**
     * Adiciona um fiel à paróquia e atualiza a contagem.
     * @param nomeParoquia nome da paróquia
     * @param fiel fiel a adicionar
     */
    public void adicionarFiel(String nomeParoquia, Fiel fiel){
        Paroquia paroquia = buscarPorNome(nomeParoquia);
        if (paroquia == null){throw new IllegalArgumentException("Paróquia não encontrada.");}
        paroquia.getFies().add(fiel);
        paroquia.setQtdeFieis(paroquia.getFies().size());
    }

    /**
     * Remove um fiel da paróquia e atualiza a contagem.
     * @param nomeParoquia nome da paróquia
     * @param fiel fiel a remover
     */
    public void removerFiel(String nomeParoquia, Fiel fiel){
        Paroquia paroquia = buscarPorNome(nomeParoquia);
        if (paroquia == null){throw new IllegalArgumentException("Paróquia não encontrada.");}
        paroquia.getFies().remove(fiel);
        paroquia.setQtdeFieis(paroquia.getFies().size());
    }


    /**
     * Retorna a quantidade de fiéis vinculados à paróquia.
     * @param nomeParoquia nome da paróquia
     * @return número de fiéis
     */
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
