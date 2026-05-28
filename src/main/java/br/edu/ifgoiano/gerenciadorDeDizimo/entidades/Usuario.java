package br.edu.ifgoiano.gerenciadorDeDizimo.entidades;

/**
 * Entidade base que representa um usuário do sistema.
 *
 * Esta classe é abstrata e serve como base para outros tipos de usuários,
 * como Fiel e Padre.
 *
 * @author Eduardo Brandão
 * @version 0.0.1
 */
public abstract class Usuario {

    /** Identificador único do usuário no banco de dados. */
    protected Long id;

    /** Nome do usuário. */
    protected String nome;

    /** E-mail do usuário. */
    protected String email;

    /** Senha do usuário armazenada em formato hash. */
    protected String senhaHash;

    /** Indica se o usuário possui permissão de administrador. */
    private boolean isAdmin;

    /** Telefone do usuário. */
    private String telefone;

    /**
     * Construtor completo da classe Usuario.
     *
     * @param id identificador do usuário
     * @param nome nome do usuário
     * @param email e-mail do usuário
     * @param senhaHash senha em formato hash
     * @param isAdmin define se o usuário é administrador
     * @param telefone telefone do usuário
     */
    public Usuario(Long id, String nome, String email, String senhaHash, boolean isAdmin, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.isAdmin = isAdmin;
        this.telefone = telefone;
    }

    /**
     * Construtor vazio da classe Usuario.
     */
    public Usuario() {
    }

    /**
     * Retorna o identificador do usuário.
     *
     * @return id do usuário
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador do usuário.
     *
     * @param id id do usuário
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o e-mail do usuário.
     *
     * @return e-mail do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     *
     * @param email e-mail do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha em formato hash.
     *
     * @return senha em hash
     */
    public String getSenhaHash() {
        return senhaHash;
    }

    /**
     * Define a senha em formato hash.
     *
     * @param senhaHash senha em hash
     */
    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    /**
     * Retorna o telefone do usuário.
     *
     * @return telefone do usuário
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do usuário.
     *
     * @param telefone telefone do usuário
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Verifica se o usuário é administrador.
     *
     * @return true se for administrador, false caso contrário
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Define se o usuário é administrador.
     *
     * @param admin true para administrador, false caso contrário
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}