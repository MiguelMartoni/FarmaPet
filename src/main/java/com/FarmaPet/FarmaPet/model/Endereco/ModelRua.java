package com.FarmaPet.FarmaPet.model.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ModelRua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRua;

    private String descricao;

    public ModelRua(String descricao, int idRua) {
        this.descricao = descricao;
        this.idRua = idRua;
    }

    public ModelRua() {

    }

    public int getIdRua() {
        return idRua;
    }

    public void setIdRua(int idRua) {
        this.idRua = idRua;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
