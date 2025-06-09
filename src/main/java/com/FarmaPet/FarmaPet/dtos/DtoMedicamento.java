package com.FarmaPet.FarmaPet.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

enum TipoUso {
    INTERNO,
    EXTERNO
}

enum MedicamentoAtivo {
    Ativo,
    Inativo

}

public record DtoMedicamento(
    @NotBlank String nome,
    @NotBlank               String principioAtivo,
    @NotBlank               String dosagem,
    @NotBlank               String especieIndicada,
    @NotNull @Future        LocalDate dataValidade,
    @NotNull                Boolean receitaObrigatoria,
    @PositiveOrZero         Float pesoIndicado,
    @PositiveOrZero         Integer idadeIndicada,
    @NotNull                TipoUso tipoUso,
    @NotNull                MedicamentoAtivo medicamentoativo,
    @NotBlank               String foto

) {}
