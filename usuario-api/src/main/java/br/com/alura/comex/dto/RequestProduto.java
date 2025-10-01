package br.com.alura.comex.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestProduto {

    @Pattern(regexp = "^[-0-9A-Za-zÀ-ÖØ-öø-ž_ ]{2,255}$", message = "Campo produto.nome precisa seguir o padrão regexp " + "^[-0-9A-Za-zÀ-ÖØ-öø-ž_ ]{2,255}$")
    @Length(max = 255, message = "Campo produto.nome precisa ter no máximo 255 caracteres")
    @Length(min = 2, message = "Campo produto.nome precisa ter no mínimo 2 caracteres")
    @NotBlank(message = "Campo produto.nome não pode ser vazio")
    @NotNull(message = "Campo produto.nome não pode ser nulo")
    private String nome;

    @Positive
    @NotNull(message = "Campo produto.preco não pode ser nulo")
    private Double preco;

    //@NotBlank(message = "Campo produto.descricao não pode ser vazio")
    private String descricao;

    @PositiveOrZero
    @NotNull(message = "Campo produto.quantidade não pode ser nulo")
    private Long quantidade;

    @PositiveOrZero
    @NotNull(message = "Campo produto.categoria não pode ser nulo")
    private Long categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    //public String getCategoria() {
    //    return categoria;
    //}
    //
    //public void setCategoria(String categoria) {
    //    this.categoria = categoria;
    //}

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public Produto toProduto() {
        return new Produto(this.nome, this.preco, this.descricao, this.quantidade, null);
    }

    public Produto toProduto(Categoria categoria) {
        return new Produto(this.nome, this.preco, this.descricao, this.quantidade, categoria);
    }
}
