package br.com.alura.comex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
    @NotNull @NotBlank String login,
    @NotNull @NotBlank String senha
) {}
