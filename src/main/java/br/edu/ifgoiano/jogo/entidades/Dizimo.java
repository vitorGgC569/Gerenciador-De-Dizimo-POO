package br.edu.ifgoiano.jogo.entidades;

import java.util.Date;

public class Dizimo{

    private String descricaoDizimo;
    private Double valor;
    private Date dataProximaContribuicao;
    private Date dataAtual;
    private Date dataInicial;
    private boolean contribuinteRecorrente;

    public String getDescricaoDizimo() {
        return descricaoDizimo;
    }

    public void setDescricaoDizimo(String descricaoDizimo) {
        this.descricaoDizimo = descricaoDizimo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public boolean isContribuinteRecorrente() {
        return contribuinteRecorrente;
    }

    public void setContribuinteRecorrente(boolean contribuinteRecorrente) {
        this.contribuinteRecorrente = contribuinteRecorrente;
    }
}
