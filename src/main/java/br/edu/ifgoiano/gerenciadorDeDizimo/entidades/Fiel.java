package br.edu.ifgoiano.gerenciadorDeDizimo.entidades;

import java.util.Date;

/**
 * Representa um fiel cadastrado no sistema.
 *
 * A classe Fiel herda os dados principais da classe Usuario
 * e acrescenta a data de batismo e o vínculo com uma paróquia.
 */
public class Fiel extends Usuario {

    /** Data em que o fiel recebeu o batismo. */
    private Date dataBatismo;

    /** Identificador da paróquia à qual o fiel pertence. */
    private Long idParoquia;

    /**
     * Construtor com a data de batismo.
     *
     * @param dataBatismo data de batismo do fiel
     */
    public Fiel(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    /**
     * Construtor vazio da classe Fiel.
     */
    public Fiel() {
    }

    public Fiel(java.sql.Date data, long l, String vitor, String mail, String number, boolean admin, String number1) {
    }

    /**
     * Retorna a data de batismo do fiel.
     *
     * @return data de batismo
     */
    public Date getDataBatismo() {
        return dataBatismo;
    }

    /**
     * Define a data de batismo do fiel.
     *
     * @param dataBatismo data de batismo
     */
    public void setDataBatismo(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    /**
     * Retorna o identificador da paróquia do fiel.
     *
     * @return id da paróquia
     */
    public Long getIdParoquia() {
        return idParoquia;
    }

    /**
     * Define o identificador da paróquia do fiel.
     *
     * @param idParoquia id da paróquia
     */
    public void setIdParoquia(Long idParoquia) {
        this.idParoquia = idParoquia;
    }
}