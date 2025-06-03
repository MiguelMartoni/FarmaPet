package com.FarmaPet.FarmaPet.DTOs;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record DtoCliente(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotNull LocalDate dataNasc,
        @NotBlank String email,
        @NotBlank String telefone,
        @NotNull Long enderecoId
) {}
