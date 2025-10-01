package br.com.alura.comex.model;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Produto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Id private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    private double preco;

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @ColumnDefault("0")
    @PositiveOrZero
    @NotNull
    private long quantidade;

    private boolean status;

    public Produto () {}

    public Produto (String nome, double preco, String descricao, long quantidade, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.status = true;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", categoria=" + categoria +
                '}';
    }
}