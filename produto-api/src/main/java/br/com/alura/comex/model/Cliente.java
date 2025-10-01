package br.com.alura.comex.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Id
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String cpf;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String nome;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;
    @NotBlank
    @NotNull
    private String telefone;
    @NotBlank
    @NotNull
    private String logradouro;
    @NotBlank
    @NotNull
    private String bairro;
    @NotBlank
    @NotNull
    private String cidade;
    @NotBlank
    @NotNull
    private String estado;
    @NotBlank
    @NotNull
    private String cep;

    @NotNull
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Usuario usuario;

    public Cliente(@NotBlank @NotNull String cpf, @NotBlank @NotNull String nome,
            @NotBlank @NotNull String email, @NotBlank @NotNull String telefone, @NotBlank @NotNull String logradouro,
            @NotBlank @NotNull String bairro, @NotBlank @NotNull String cidade, @NotBlank @NotNull String estado,
            @NotBlank @NotNull String cep, @NotNull Usuario usuario) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.usuario = usuario;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", usuario='" + (usuario == null ? "null" : usuario.getEmail()) + '\'' +
                '}';
    }
}
