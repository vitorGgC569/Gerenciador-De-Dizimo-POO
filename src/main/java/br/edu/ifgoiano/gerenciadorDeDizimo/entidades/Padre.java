package br.edu.ifgoiano.gerenciadorDeDizimo.entidades;

import java.util.Date;

/**
 * Representa um padre cadastrado no sistema.
 *
 * A classe Padre herda os dados principais da classe Usuario
 * e acrescenta a data de ordenação.
 */
public class Padre extends Usuario {

    /** Data em que o padre foi ordenado. */
    private Date data_Ordenacao;

    /**
     * Construtor com a data de ordenação.
     *
     * @param data_Ordenacao data de ordenação do padre
     */
    public Padre(Date data_Ordenacao) {
        this.data_Ordenacao = data_Ordenacao;
    }

    /**
     * Construtor vazio da classe Padre.
     */
    public Padre() {
    }

    public Padre(java.sql.Date data, long l, String nome, String email, String senha, boolean admin, String telefone) {
    }

    /**
     * Retorna a data de ordenação do padre.
     *
     * @return data de ordenação
     */
    public Date getData_Ordenacao() {
        return data_Ordenacao;
    }

    /**
     * Define a data de ordenação do padre.
     *
     * @param data_Ordenacao data de ordenação
     */
    public void setData_Ordenacao(Date data_Ordenacao) {
        this.data_Ordenacao = data_Ordenacao;
    }
}