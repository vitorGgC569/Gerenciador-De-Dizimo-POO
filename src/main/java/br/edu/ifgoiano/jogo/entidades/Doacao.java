package br.edu.ifgoiano.jogo.entidades;

/**
 * Representa uma doação realizada por um fiel para uma paróquia.
 *
 * A classe armazena o valor da doação, o tipo da doação,
 * o identificador do fiel e o identificador da paróquia.
 */
public class Doacao {

    /** Identificador único da doação no banco de dados. */
    private Long id;

    /** Valor da doação. */
    private double valor;

    /** Tipo da doação, como Dízimo, Oferta, Campanha ou Caridade. */
    private String tipo;

    /** Identificador do fiel que realizou a doação. */
    private Long idFiel;

    /** Identificador da paróquia que recebeu a doação. */
    private Long idParoquia;

    /**
     * Construtor completo da classe Doacao.
     *
     * @param id identificador da doação
     * @param valor valor da doação
     * @param tipo tipo da doação
     * @param idFiel identificador do fiel
     * @param idParoquia identificador da paróquia
     */
    public Doacao(Long id, double valor, String tipo, Long idFiel, Long idParoquia) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.idFiel = idFiel;
        this.idParoquia = idParoquia;
    }

    /**
     * Construtor usado para criar uma nova doação antes de inserir no banco.
     *
     * @param valor valor da doação
     * @param tipo tipo da doação
     * @param idFiel identificador do fiel
     * @param idParoquia identificador da paróquia
     */
    public Doacao(double valor, String tipo, Long idFiel, Long idParoquia) {
        this.valor = valor;
        this.tipo = tipo;
        this.idFiel = idFiel;
        this.idParoquia = idParoquia;
    }

    /**
     * Construtor vazio da classe Doacao.
     */
    public Doacao() {
    }

    /**
     * Retorna o identificador da doação.
     *
     * @return id da doação
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador da doação.
     *
     * @param id id da doação
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o valor da doação.
     *
     * @return valor da doação
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da doação.
     *
     * @param valor valor da doação
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna o tipo da doação.
     *
     * @return tipo da doação
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da doação.
     *
     * @param tipo tipo da doação
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna o identificador do fiel.
     *
     * @return id do fiel
     */
    public Long getIdFiel() {
        return idFiel;
    }

    /**
     * Define o identificador do fiel.
     *
     * @param idFiel id do fiel
     */
    public void setIdFiel(Long idFiel) {
        this.idFiel = idFiel;
    }

    /**
     * Retorna o identificador da paróquia.
     *
     * @return id da paróquia
     */
    public Long getIdParoquia() {
        return idParoquia;
    }

    /**
     * Define o identificador da paróquia.
     *
     * @param idParoquia id da paróquia
     */
    public void setIdParoquia(Long idParoquia) {
        this.idParoquia = idParoquia;
    }


}