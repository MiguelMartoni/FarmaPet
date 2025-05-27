package com.FarmaPet.FarmaPet.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DtoEndereco(
        Long ruaId,
        Long bairroId,
        Long cidadeId,
        Long ufId,
        @NotBlank String cep,
        String numero
) {}

