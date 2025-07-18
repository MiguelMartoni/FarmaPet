package com.FarmaPet.FarmaPet.model.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class ModelEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEndereco;

    private String cep;
    private String numero;

    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    @Column(length = 100)
    private String complemento; // Adicionei este campo que estava faltando

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private ModelBairro bairro;

    @ManyToOne
    @JoinColumn(name = "id_rua")
    private ModelRua rua;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private ModelCidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_uf")
    private ModelUf uf;

    public ModelEndereco() {
        // Construtor padrão
    }

    public ModelEndereco(ModelBairro bairro, String cep, ModelCidade cidade, String complemento, long idEndereco, String numero, ModelRua rua, ModelUf uf) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.idEndereco = idEndereco;
        this.numero = numero;
        this.rua = rua;
        this.uf = uf;
    }

    // Getters e Setters

    public long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ModelBairro getBairro() {
        return bairro;
    }

    public void setBairro(ModelBairro bairro) {
        this.bairro = bairro;
    }

    public ModelRua getRua() {
        return rua;
    }

    public void setRua(ModelRua rua) {
        this.rua = rua;
    }

    public ModelCidade getCidade() {
        return cidade;
    }

    public void setCidade(ModelCidade cidade) {
        this.cidade = cidade;
    }

    public ModelUf getUf() {
        return uf;
    }

    public void setUf(ModelUf uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}