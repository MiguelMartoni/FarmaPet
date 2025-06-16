package com.FarmaPet.FarmaPet.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DtoMovimentacaoEstoqueMultipla(
    @Min(1) int id,  // ID do medicamento, deve ser > 0
    @NotNull DtoMovimentacaoEstoque.TipoMovimentacao tipo,
    @Min(1) int quantidade
) {}