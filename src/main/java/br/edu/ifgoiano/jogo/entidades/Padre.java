package br.edu.ifgoiano.jogo.entidades;

import java.util.Date;

/** Representa um padre, estendendo {@link Usuario}. */
public class Padre extends Usuario{

    /** Data em que o padre foi ordenado. */
    private Date data_Ordenacao;

    /**
     * @return data de ordenação do padre
     */
    public Date getData_Ordenacao() {
        return data_Ordenacao;
    }

    /**
     * @param data_Ordenacao data de ordenação a definir
     */
    public void setData_Ordenacao(Date data_Ordenacao) {
        this.data_Ordenacao = data_Ordenacao;
    }
}