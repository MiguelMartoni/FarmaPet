package com.FarmaPet.FarmaPet.model.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Name;

@Entity
public class ModelUf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("id_uf")
    private int idUf;


    @Column(name = "sigla", nullable = false, unique = true, length = 2)
    private String sigla;

     @Column(name = "descricao", nullable = false)
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
