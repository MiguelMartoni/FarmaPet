package com.FarmaPet.FarmaPet.Model.Endereço;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ModelBairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBairro;

    private String descricao;

    public ModelBairro(String descricao, int idBairro) {
        this.descricao = descricao;
        this.idBairro = idBairro;
    }

    public ModelBairro() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

   
}