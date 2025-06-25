package com.FarmaPet.FarmaPet.dtos;

import jakarta.validation.constraints.NotBlank;

public record DtoEndereco(
        Long ruaId,
        Long bairroId,
        Long cidadeId,
        Long ufId,
        @NotBlank String cep,
        String numero,
        String complemento
) {}

