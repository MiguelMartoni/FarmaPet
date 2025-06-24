package com.FarmaPet.FarmaPet.model;

import java.time.LocalDate;
import java.util.List;

import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ModelCliente extends com.FarmaPet.FarmaPet.model.ModelPessoa {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ModelAnimal> animais;

    public ModelCliente() {}

    public ModelCliente(String nome, String cpf, LocalDate dataNasc, ModelEndereco endereco, String email, String telefone, List<ModelAnimal> animais) {
        super(nome, cpf, dataNasc, endereco, email, telefone);
        this.animais = animais;
    }

    public List<ModelAnimal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<ModelAnimal> animais) {
        this.animais = animais;
    }
}
