package com.FarmaPet.FarmaPet.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DtoAnimal(
        @NotBlank String nome,
        @NotBlank String especie,
        @NotBlank String raca,
        @Min(0) Integer idade,
        @Positive Float peso,
        @NotNull Integer clienteId,
        String foto
) {}
