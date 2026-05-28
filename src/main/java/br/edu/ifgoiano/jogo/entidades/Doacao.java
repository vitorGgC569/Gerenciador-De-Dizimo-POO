package br.edu.ifgoiano.jogo.entidades;

import java.util.Date;

/**
 * Classe centralizada para as ofertas dos fieis
 */
public class Doacao{

    // Variaveis de Instância.

   private double valor;
   private String tipo;
   private String nomeParoquia;
   private long IdFiel;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeParoquia() {
        return nomeParoquia;
    }

    public void setNomeParoquia(String nomeParoquia) {
        this.nomeParoquia = nomeParoquia;
    }

    public long getIdFiel() {
        return IdFiel;
    }

    public void setIdFiel(long idFiel) {
        IdFiel = idFiel;
    }
}
