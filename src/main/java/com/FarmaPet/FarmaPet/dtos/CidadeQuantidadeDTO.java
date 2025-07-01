package com.FarmaPet.FarmaPet.dtos;

public class CidadeQuantidadeDTO {
    private String cidade;
    private Long quantidade;

    public CidadeQuantidadeDTO(String cidade, Long quantidade) {
        this.cidade = cidade;
        this.quantidade = quantidade;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getQuantidade() {
        return quantidade;
    }
}