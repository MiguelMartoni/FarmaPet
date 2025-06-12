package com.FarmaPet.FarmaPet.model.endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ModelCidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCidade;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_uf")
    private ModelUf uf;

    public ModelCidade(String descricao, int idCidade, ModelUf uf) {
        this.descricao = descricao;
        this.idCidade = idCidade;
        this.uf = uf;
    }

    public ModelCidade() {

    }

    // Getters and Setters

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ModelUf getUf() {
        return uf;
    }

    public void setUf(ModelUf uf) {
        this.uf = uf;
    }
}
