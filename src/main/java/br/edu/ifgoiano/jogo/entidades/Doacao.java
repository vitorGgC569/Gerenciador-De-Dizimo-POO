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
   private Long id;

    public Doacao(double valor, String tipo, String nomeParoquia, long idFiel, Long id) {
        this.valor = valor;
        this.tipo = tipo;
        this.nomeParoquia = nomeParoquia;
        IdFiel = idFiel;
        this.id = id;
    }

    public Doacao() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
