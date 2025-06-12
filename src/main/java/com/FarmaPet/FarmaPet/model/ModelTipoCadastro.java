package com.FarmaPet.FarmaPet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_cadastro")
public class ModelTipoCadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipo_cadastro_id;

    @Column(length = 70, nullable = false)
    private String nome;

    public Long getId() {
        return tipo_cadastro_id;
    }

    public void setId(Long id) {
        this.tipo_cadastro_id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
