package com.FarmaPet.FarmaPet.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCliente(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotNull LocalDate dataNasc,
        @NotBlank String email,
        @NotBlank String telefone,
        Long enderecoId,
        String foto
) {}