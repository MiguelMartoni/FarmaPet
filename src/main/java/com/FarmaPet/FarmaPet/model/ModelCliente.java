package com.FarmaPet.FarmaPet.model;

import java.time.LocalDate;
import java.util.List;

import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class ModelCliente extends ModelPessoa {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ModelAnimal> animais;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String foto;

    public ModelCliente(){}

    public ModelCliente(String nome, String cpf, LocalDate dataNasc, ModelEndereco endereco, String email, String telefone, List<ModelAnimal> animais, String foto) {
        super(nome, cpf, dataNasc, endereco, email, telefone);
        this.animais = animais;
        this.foto = foto;
    }

    public List<ModelAnimal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<ModelAnimal> animais) {
        this.animais = animais;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
