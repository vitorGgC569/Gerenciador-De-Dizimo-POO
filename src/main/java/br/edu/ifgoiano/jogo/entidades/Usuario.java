package br.edu.ifgoiano.jogo.entidades;

/**
 * Entidade que armazena a entidade central Usuario.
 * E todos seus atributos
 * @author Eduardo Brandão
 * @version 0.0.1
 */
public abstract class Usuario {
    protected Long id;
    protected String nome;
    protected String email;
    protected String senhaHash;
    private boolean isAdmin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
