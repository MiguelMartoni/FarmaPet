package com.FarmaPet.FarmaPet.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DtoFuncionario(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String telefone,
        @NotBlank String email,
        @NotBlank String nomeUsuario,
        @NotBlank String senha,
        @NotNull Long tipoCadastroId,
        @NotNull Boolean usuarioAtivo,
        @NotNull LocalDate dataNasc,
        @NotNull Long enderecoId
) {}
