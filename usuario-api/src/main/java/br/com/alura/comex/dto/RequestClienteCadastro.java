package br.com.alura.comex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestClienteCadastro {
    @NotNull
    @NotBlank
    String email;
    
    @NotNull
    @NotBlank
    String senha;

    @NotBlank
    @NotNull
    private String cpf;
    @NotBlank
    @NotNull
    private String nome;
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

    public RequestClienteCadastro(@NotNull @NotBlank String email, @NotNull @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public RequestClienteCadastro(@NotNull @NotBlank String email, @NotNull @NotBlank String senha,
            @NotBlank @NotNull String cpf, @NotBlank @NotNull String nome, @NotBlank @NotNull String telefone,
            @NotBlank @NotNull String logradouro, @NotBlank @NotNull String bairro, @NotBlank @NotNull String cidade,
            @NotBlank @NotNull String estado, @NotBlank @NotNull String cep) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }



    public RequestClienteCadastro() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.email;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    @Override
    public String toString() {
        return "Cliente{" +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
