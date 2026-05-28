package br.edu.ifgoiano.jogo.entidades;

import java.util.ArrayList;

/** Representa uma paróquia, contendo seus fiéis e o padre responsável. */
public class Paroquia {

    /** Nome da paróquia. */
    private String nomeParoquia;

    /** Endereço da paróquia. */
    private String endereco;

    /** Lista de fiéis vinculados à paróquia. */
    private ArrayList<Fiel> fies;

    /** Quantidade atual de fiéis. */
    private int qtdeFieis;

    /** Padre responsável pela paróquia. */
    private Padre padre;

    /**
     * @param nomeParoquia nome da paróquia
     * @param endereco endereço da paróquia
     * @param padre padre responsável
     */
    public Paroquia(String nomeParoquia, String endereco, Padre padre){
        this.fies = new ArrayList<>();
        this.qtdeFieis = fies.size();
    }

    /** @return nome da paróquia */
    public String getNomeParoquia() {
        return nomeParoquia;
    }

    /** @param nomeParoquia nome a definir */
    public void setNomeParoquia(String nomeParoquia) {
        this.nomeParoquia = nomeParoquia;
    }

    /** @return endereço da paróquia */
    public String getEndereco() {
        return endereco;
    }

    /** @param endereco endereço a definir */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /** @return quantidade de fiéis cadastrados */
    public int getQtdeFieis() {
        return qtdeFieis;
    }

    /** @param qtdeFieis quantidade a definir */
    public void setQtdeFieis(int qtdeFieis) {
        this.qtdeFieis = qtdeFieis;
    }

    /** @return lista de fiéis da paróquia */
    public ArrayList<Fiel> getFies() {
        return fies;
    }

    /** @param fies lista de fiéis a definir */
    public void setFies(ArrayList<Fiel> fies) {
        this.fies = fies;
    }
}
