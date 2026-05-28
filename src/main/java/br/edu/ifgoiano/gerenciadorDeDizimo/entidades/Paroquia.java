package br.edu.ifgoiano.gerenciadorDeDizimo.entidades;

import java.util.ArrayList;

/**
 * Representa uma paróquia cadastrada no sistema.
 *
 * A classe armazena o nome, endereço, padre responsável
 * e a lista de fiéis vinculados à paróquia.
 */
public class Paroquia {

    /** Identificador único da paróquia no banco de dados. */
    private Long id;

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
     * Construtor completo da classe Paroquia.
     *
     * @param nomeParoquia nome da paróquia
     * @param endereco endereço da paróquia
     * @param fies lista de fiéis
     * @param qtdeFieis quantidade de fiéis
     * @param padre padre responsável
     */
    public Paroquia(String nomeParoquia, String endereco, ArrayList<Fiel> fies, int qtdeFieis, Padre padre) {
        this.nomeParoquia = nomeParoquia;
        this.endereco = endereco;
        this.fies = fies;
        this.padre = padre;
    }

    /**
     * Construtor vazio da classe Paroquia.
     */
    public Paroquia() {
        this.fies = new ArrayList<>();
        this.qtdeFieis = 0;
    }

    /**
     * Construtor básico da classe Paroquia.
     *
     * @param nomeParoquia nome da paróquia
     * @param endereco endereço da paróquia
     * @param padre padre responsável
     */
    public Paroquia(String nomeParoquia, String endereco, Padre padre) {
        this.nomeParoquia = nomeParoquia;
        this.endereco = endereco;
        this.padre = padre;
        this.fies = new ArrayList<>();
        this.qtdeFieis = this.fies.size();
    }

    /**
     * Retorna o identificador da paróquia.
     *
     * @return id da paróquia
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador da paróquia.
     *
     * @param id id da paróquia
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome da paróquia.
     *
     * @return nome da paróquia
     */
    public String getNomeParoquia() {
        return nomeParoquia;
    }

    /**
     * Define o nome da paróquia.
     *
     * @param nomeParoquia nome da paróquia
     */
    public void setNomeParoquia(String nomeParoquia) {
        this.nomeParoquia = nomeParoquia;
    }

    /**
     * Retorna o endereço da paróquia.
     *
     * @return endereço da paróquia
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da paróquia.
     *
     * @param endereco endereço da paróquia
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna a quantidade de fiéis.
     *
     * @return quantidade de fiéis
     */
    public int getQtdeFieis() {
        return qtdeFieis;
    }

    /**
     * Define a quantidade de fiéis.
     *
     * @param qtdeFieis quantidade de fiéis
     */
    public void setQtdeFieis(int qtdeFieis) {
        this.qtdeFieis = qtdeFieis;
    }

    /**
     * Retorna a lista de fiéis da paróquia.
     *
     * @return lista de fiéis
     */
    public ArrayList<Fiel> getFies() {
        return fies;
    }

    /**
     * Define a lista de fiéis da paróquia.
     *
     * @param fies lista de fiéis
     */
    public void setFies(ArrayList<Fiel> fies) {
        this.fies = fies;
        this.qtdeFieis = fies != null ? fies.size() : 0;
    }

    /**
     * Retorna o padre responsável pela paróquia.
     *
     * @return padre responsável
     */
    public Padre getPadre() {
        return padre;
    }

    /**
     * Define o padre responsável pela paróquia.
     *
     * @param padre padre responsável
     */
    public void setPadre(Padre padre) {
        this.padre = padre;
    }
}