package br.com.alura.comex.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Categoria {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Id private Long id;

    @NotNull(message = "nome da categoria não pode ser nulo")
    @NotBlank(message = "nome da categoria não pode ser vazio")
    @Length(min = 2, message = "nome da categoria precisa ter no mínimo 2 caracteres")
    @Length(max = 255, message = "nome da categoria precisa ter no máximo 255 caracteres")
    @Column(unique = true)
    @NotNull @NotBlank private String nome;
    
    private boolean status;
    
    public Categoria(String nome) {
        this.nome = nome;
        this.status = true;
    }

    public Categoria() {
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nome=" + nome + ", status=" + status + "]";
    }

}