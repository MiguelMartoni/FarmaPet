package com.FarmaPet.FarmaPet.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DtoMovimentacaoEstoque(
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    Integer quantidade,

    @NotNull(message = "Tipo de movimentação é obrigatório") // ENTRADA ou SAIDA
    TipoMovimentacao tipo
) {
    public enum TipoMovimentacao {
        ENTRADA, SAIDA
    }
}
