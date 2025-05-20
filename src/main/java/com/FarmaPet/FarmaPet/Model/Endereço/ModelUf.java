package com.FarmaPet.FarmaPet.Model.Endereço;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ModelUf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUf;

    private String sigla;
    private String descricao;

    // Getters and Setters

    public int getIdUf() {
        return idUf;
    }

    public void setIdUf(int idUf) {
        this.idUf = idUf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
