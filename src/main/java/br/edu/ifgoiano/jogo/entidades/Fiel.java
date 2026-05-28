package br.edu.ifgoiano.jogo.entidades;

import java.util.Date;

/** Representa um fiel da paróquia, estendendo {@link Usuario}. */
public class Fiel extends Usuario{

    /** Data em que o fiel recebeu o batismo. */
    private Date dataBatismo;

    /**
     * @return data de batismo do fiel
     */
    public Date getDataBatismo() {
        return dataBatismo;
    }

    /**
     * @param dataBatismo data de batismo a definir
     */
    public void setDataBatismo(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

}