package br.edu.ifgoiano.jogo.entidades;

import java.util.Date;

public class Padre extends Usuario{
    private Date data_Ordenacao;

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getData_Ordenacao() {
        return data_Ordenacao;
    }

    public void setData_Ordenacao(Date data_Ordenacao) {
        this.data_Ordenacao = data_Ordenacao;
    }
}