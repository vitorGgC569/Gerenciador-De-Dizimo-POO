package br.edu.ifgoiano.jogo.entidades;

import java.util.ArrayList;

public class Paroquia {
    private String nomeParoquia;
    private String endereco;
    private ArrayList<Fiel> fies;
    private int qtdeFieis;
    private Padre padre;

    public Paroquia(String nomeParoquia, String endereco, Padre padre){
        this.fies = new ArrayList<>();
        this.qtdeFieis = fies.size();
    }

    public String getNomeParoquia() {
        return nomeParoquia;
    }

    public void setNomeParoquia(String nomeParoquia) {
        this.nomeParoquia = nomeParoquia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQtdeFieis() {
        return qtdeFieis;
    }

    public void setQtdeFieis(int qtdeFieis) {
        this.qtdeFieis = qtdeFieis;
    }

    public ArrayList<Fiel> getFies() {
        return fies;
    }

    public void setFies(ArrayList<Fiel> fies) {
        this.fies = fies;
    }
}
