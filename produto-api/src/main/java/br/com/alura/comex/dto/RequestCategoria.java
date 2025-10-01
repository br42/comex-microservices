package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestCategoria {

    @Pattern(regexp = "^[-0-9A-Za-zÀ-ÖØ-öø-ž_ ]{2,255}$", message = "Campo categoria.nome precisa seguir o padrão regexp " + "^[-0-9A-Za-zÀ-ÖØ-öø-ž_ ]{2,255}$")
    @Size(max = 255, message = "Campo categoria.nome precisa ter no máximo 255 caracteres")
    @Size(min = 2, message = "Campo categoria.nome precisa ter no mínimo 2 caracteres")
    @NotBlank(message = "Campo categoria.nome não pode ser vazio")
    @NotNull(message = "Campo categoria.nome não pode ser nulo")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toCategoria() {
        return new Categoria(this.nome);
    }
}
